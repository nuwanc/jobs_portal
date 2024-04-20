import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Table, Container } from 'reactstrap';
import { Link } from "react-router-dom";


function Skill() {
    const [skills, setSkills] = useState([]);

    useEffect(() => {
        fetch('/api/skill')
            .then(response => response.json())
            .then(data => setSkills(data))
            .catch(error => console.error('Error fetching skills:', error));
    }, []);


    const remove = async (id) => {
        await fetch(`/api/skill/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedSkills = [...skills].filter(i => i.id !== id);
            setSkills(updatedSkills);
        });
    }

    const skillsList = skills.map((skill, index) => {
        return <tr key={skill.id}>
            <th scope="row">{index + 1}</th>
            <td>{skill.skillName}</td>
            <td>{skill.noOfYears}</td>
            <td>{skill.skillRating}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="danger" onClick={() => remove(skill.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <>
            <Container className="bg-light border">
                <div className="float-end">
                    <Button color="success" tag={Link} to="/skills.do/new">Add Skill</Button>
                </div>
                <h2>Skills</h2>
                <Table bordered>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Skill</th>
                            <th>No Of Years</th>
                            <th>Skill Rating</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {skillsList}
                    </tbody>
                </Table>
            </Container>
        </>
    );
}

export default Skill;