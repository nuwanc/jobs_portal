import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Table, Container } from 'reactstrap';
import { Link } from "react-router-dom";

function Experience() {
    const [experiences, setExperiences] = useState([]);

    useEffect(() => {
        fetch('/api/experience')
            .then(response => response.json())
            .then(data => setExperiences(data))
            .catch(error => console.error('Error fetching experiences:', error));
    }, []);

   

    const remove = async (id) => {
        await fetch(`/api/experience/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedExperiences = [...experiences].filter(i => i.id !== id);
            setExperiences(updatedExperiences);
        });
    }

    const experiencesList = experiences.map(experience => {
        return <tr key={experience.id}>
            <td scope="row">{experience.jobTitle}</td>
            <td>{experience.company}</td>
            <td>{experience.fromDate}</td>
            <td>{experience.toDate}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="danger" onClick={() => remove(experience.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <>
            <Container className="bg-light border">
                <h2>Experiences</h2>
                <div className="float-end">
                    <Button color="success" tag={Link} to="/experience.do/new">Add Experience</Button>
                </div>
                <Table bordered>
                    <thead>
                        <tr>
                            <th>Job Title</th>
                            <th>Company</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {experiencesList}
                    </tbody>
                </Table>
                
            </Container>
        </>
    );
}

export default Experience;