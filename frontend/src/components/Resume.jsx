import React, { useEffect, useState } from 'react';
import { Table, Container } from 'reactstrap';
import { jsPDF } from "jspdf";
import html2canvas from "html2canvas";

function Resume() {
    const [profile, setProfile] = useState({});
    const [skills, setSkills] = useState([]);
    const [educations,setEducations] = useState([]);
    const [experiences, setExperiences] = useState([]);
    const [professionals, setProfessionals] = useState([]);

    useEffect(() => {
        fetch('/api/profile')
            .then(response => response.json())
            .then(data => setProfile(data))
            .catch(error => console.error('Error fetching profile:', error));
    }, []);

    useEffect(() => {
        fetch('/api/skill')
            .then(response => response.json())
            .then(data => setSkills(data))
            .catch(error => console.error('Error fetching skills:', error));
    }, []);

    useEffect(() => {
        fetch('/api/education/level')
            .then(response => response.json())
            .then(data => setEducations(data))
            .catch(error => console.error('Error fetching educations:', error));
    }, []);

    useEffect(() => {
        fetch('/api/experience')
            .then(response => response.json())
            .then(data => setExperiences(data))
            .catch(error => console.error('Error fetching experiences:', error));
    }, []);

    useEffect(() => {
        fetch('/api/professional')
            .then(response => response.json())
            .then(data => setProfessionals(data))
            .catch(error => console.error('Error fetching professional:', error));
    }, []);

    const professionalsList = professionals.map((professional) => {
        return <div class="list-group-item">
            <h5 class="mb-1">{professional.name}</h5>
            <p class="mb-1"><strong>Issued By:</strong> {professional.professionalBody}</p>
            <p class="mb-1"><strong>Obtained:</strong> {professional.obtainedDate}</p>
        </div>
    });

    const skillsList = skills.map((skill, index) => {
        return <li class="mb-2">
            <strong>{skill.skillName}: </strong> {skill.noOfYears} years
            <span class="float-right"> {skill.skillRating}</span>
        </li>
    });

    const experiencesList = experiences.map(experience => {
        return <div class="list-group-item">
            <h5 class="mb-1">{experience.jobTitle}</h5>
            <p class="mb-1"><strong>Company:</strong> {experience.company}</p>
            <p class="mb-1"><strong>Duration:</strong> {experience.fromDate} - {experience.toDate}</p>
        </div>
    });

    const educationsList = educations.map(education => {
        return <div class="list-group-item">
            <h5 class="mb-1">{education.institute}</h5>
            <p class="mb-1">{education.level}</p>
            <p class="mb-1"><strong>Duration:</strong> {education.startDate} - {education.endDate}</p>
        </div>
    });

        const downloadPdf = () => {
            const input = document.getElementById('resume'); // Make sure 'resume' is the ID of the element you want to capture
            html2canvas(input, {
              scale: 2, // Adjust scale factor as needed for better resolution
              useCORS: true // Helps with loading images from external URLs if needed
            }).then((canvas) => {
              const imgData = canvas.toDataURL('image/png');
              const pdf = new jsPDF('p', 'mm', 'a4');
              let imgWidth = 210; // A4 width in mm
              let pageHeight = 297;  // A4 height in mm
              let imgHeight = canvas.height * imgWidth / canvas.width;
              let heightLeft = imgHeight;

              let position = 0;
              pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
              heightLeft -= pageHeight;

              while (heightLeft >= 0) {
                position = heightLeft - imgHeight;
                pdf.addPage();
                pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
                heightLeft -= pageHeight;
              }
              pdf.save('Resume-'+profile.name+'.pdf');
            });
          };


    return (
        <>
            <Container className="bg-light border">
                <button onClick={downloadPdf}>Download as PDF</button>
                <div class="container mt-5" id="resume">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="text-center mb-3">Resume of {profile.name}</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Personal Information</h3>
                            <p><strong>Name:</strong>{profile.name}</p>
                            <p><strong>Age:</strong>{profile.age}</p>
                            <p><strong>Date of Birth:</strong>{profile.dob}</p>
                            <p><strong>Address:</strong>{profile.address}</p>
                            <p><strong>Email:</strong>{profile.email}</p>
                            <p><strong>Mobile:</strong>{profile.mobile}</p>
                            <p><strong>Website:</strong> <a href={'{profile.website}'}>{profile.website}</a></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Skills</h3>
                            <ul class="list-unstyled">
                                {skillsList}
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Education</h3>
                            <div class="list-group">
                                {educationsList}
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Experience</h3>
                            <div class="list-group">
                                {experiencesList}
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Professional Qualifications</h3>
                            <div class="list-group">
                                {professionalsList}
                            </div>
                        </div>
                    </div>

                </div>
            </Container>
        </>
    );
}

export default Resume;