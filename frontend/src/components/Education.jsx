import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Table, Container } from 'reactstrap';
import { Link } from "react-router-dom";

function Education() {
    const [educations,setEducations] = useState([]);

    useEffect(() => {
        fetch('/api/education')
            .then(response => response.json())
            .then(data => setEducations(data))
            .catch(error => console.error('Error fetching educations:', error));
    }, []);


    const remove = async (id) => {
        await fetch(`/api/education/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedEducations = [...educations].filter(i => i.id !== id);
            setSkills(updatedEducations);
        });
    }

    const educationsList = educations.map((education, index) => {
        return <tr key={education.id}>
            <th scope="row">{index + 1}</th>
            <td>{education.institute}</td>
            <td>{education.degree}</td>
            <td>{education.level}</td>
            <td>{education.field}</td>
            <td>{education.startDate}</td>
            <td>{education.endDate}</td>
            <td>{education.passes}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="danger" onClick={() => remove(education.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <>
            <Container className="bg-light border">
                <div className="float-end">
                    <Button color="success" tag={Link} to="/education.do/new">Add Education</Button>
                </div>
                <h2>Skills</h2>
                <Table bordered>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Institute</th>
                            <th>Degree</th>
                            <th>Level</th>
                            <th>field</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Passes</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {educationsList}
                    </tbody>
                </Table>
            </Container>
        </>
    );

}

export default Education;