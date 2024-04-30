import React, { useEffect, useState } from 'react';
import { Table, Container } from 'reactstrap';
import { useParams } from 'react-router-dom';

function AgentResume() {
    const [resume, setResume] = useState({
        profile:{},
        professionalList:[],
        skillList:[],
        experienceList:[],
        educationList:[]
    });
    let { id } = useParams();

    useEffect(() => {
        fetch('/api/resume/'+id)
            .then(response => response.json())
            .then(data => setResume(data))
            .catch(error => console.error('Error fetching profile:', error));
    }, []);

    const professionalsList = resume.professionalList.map((professional) => {
        return <div class="list-group-item">
            <h5 class="mb-1">{professional.name}</h5>
            <p class="mb-1"><strong>Issued By:</strong> {professional.professionalBody}</p>
            <p class="mb-1"><strong>Obtained:</strong> {professional.obtainedDate}</p>
        </div>
    });

    const skillsList = resume.skillList.map((skill, index) => {
        return <li class="mb-2">
            <strong>{skill.skillName}: </strong> {skill.noOfYears} years
            <span class="float-right"> {skill.skillRating}</span>
        </li>
    });

    const experiencesList = resume.experienceList.map(experience => {
        return <div class="list-group-item">
            <h5 class="mb-1">{experience.jobTitle}</h5>
            <p class="mb-1"><strong>Company:</strong> {experience.company}</p>
            <p class="mb-1"><strong>Duration:</strong> {experience.fromDate} - {experience.toDate}</p>
        </div>
    });

    const educationsList = resume.educationList.map(education => {
        return <div class="list-group-item">
            <h5 class="mb-1">{education.institute}</h5>
            <p class="mb-1">{education.level}</p>
            <p class="mb-1"><strong>Duration:</strong> {education.startDate} - {education.endDate}</p>
        </div>
    });

    return (
        <>
            <Container className="bg-light border">

                <div class="container mt-5">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="text-center mb-3">Resume of {resume.profile.name}</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Personal Information</h3>
                            <p><strong>Name:</strong>{resume.profile.name}</p>
                            <p><strong>Age:</strong>{resume.profile.age}</p>
                            <p><strong>Date of Birth:</strong>{resume.profile.dob}</p>
                            <p><strong>Address:</strong>{resume.profile.address}</p>
                            <p><strong>Email:</strong>{resume.profile.email}</p>
                            <p><strong>Mobile:</strong>{resume.profile.mobile}</p>
                            <p><strong>Website:</strong> <a href={'{resume.profile.website}'}>{resume.profile.website}</a></p>
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

export default AgentResume;