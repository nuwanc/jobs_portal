import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Container } from 'reactstrap';


function ProfessionalEdit() {
    const [professional, setProfessional] = useState({});
    
    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/professional', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(professional),
        })
            .then(response => response.json())
            .then((data) => setProfessional({}))
            .catch(error => console.error('Error creating professional:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setProfessional(prevProfessional => ({ ...prevProfessional, [name]: value }));
    };
    return(
        <>
            <h2>Professional Qualification</h2>
            <Container className="bg-light border">
            <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="jobTitle">Qualification</Label>
                        <Input type="text" name="name" id="name" placeholder="Qualification" onChange={handleChange} value={professional.name} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="company">Body</Label>
                        <Input type="text" name="professionalBody" id="professionalBody" placeholder="Body" onChange={handleChange} value={professional.professionalBody} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="fromDate">Date Obtained</Label>
                        <Input type="date" name="obtainedDate" id="obtainedDate" placeholder="Obtained since" onChange={handleChange} value={professional.obtainedDate} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="toDate">To Date</Label>
                        <Input type="date" name="expiryDate" id="expiryDate" placeholder="Valid Till" onChange={handleChange} value={professional.expiryDate} />
                    </FormGroup>
                    <Button type='submit'>Submit</Button>
                </Form>
            </Container>
        </>
    );

}

export default ProfessionalEdit;