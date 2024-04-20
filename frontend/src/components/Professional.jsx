import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Table, Container } from 'reactstrap';
import { Link } from "react-router-dom";

function Professional() {

   const [professionals, setProfessionals] = useState([]);

    useEffect(() => {
        fetch('/api/professional')
            .then(response => response.json())
            .then(data => setProfessionals(data))
            .catch(error => console.error('Error fetching professional:', error));
    }, []);

   

    const remove = async (id) => {
        await fetch(`/api/professional/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedProfessionals = [...professionals].filter(i => i.id !== id);
            setProfessionals(updatedProfessionals);
        });
    }

    const professionalsList = professionals.map(professional => {
        return <tr key={professional.id}>
            <td scope="row">{professional.name}</td>
            <td>{professional.professionalBody}</td>
            <td>{professional.obtainedDate}</td>
            <td>{professional.expiryDate}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="danger" onClick={() => remove(professional.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <>
            <Container className="bg-light border">
                <h2>Professional Qualifications</h2>
                <div className="float-end">
                    <Button color="success" tag={Link} to="/professional.do/new">Add Professional</Button>
                </div>
                <Table bordered>
                    <thead>
                        <tr>
                            <th>Qualification</th>
                            <th>Body</th>
                            <th>Date Obtained</th>
                            <th>Valid Till</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {professionalsList}
                    </tbody>
                </Table>
                
            </Container>
        </>
    );
}

export default Professional;