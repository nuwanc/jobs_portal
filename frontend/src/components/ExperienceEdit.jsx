import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Container } from 'reactstrap';


function ExperienceEdit() {
    const [experience, setExperience] = useState({});
    
    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/experience', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(experience),
        })
            .then(response => response.json())
            .then((data) => setExperience({}))
            .catch(error => console.error('Error creating experience:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setExperience(prevExperience => ({ ...prevExperience, [name]: value }));
    };
    return(
        <>
            <h2>Add Experience</h2>
            <Container className="bg-light border">
            <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="jobTitle">Job Title</Label>
                        <Input type="text" name="jobTitle" id="jobTitle" placeholder="job title" onChange={handleChange} value={experience.jobTitle} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="company">Company</Label>
                        <Input type="text" name="company" id="company" placeholder="company" onChange={handleChange} min={1} max={100} value={experience.company} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="fromDate">From Date</Label>
                        <Input type="date" name="fromDate" id="fromDate" placeholder="from date" onChange={handleChange} value={experience.fromDate} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="toDate">To Date</Label>
                        <Input type="date" name="toDate" id="toDate" placeholder="to date" onChange={handleChange} value={experience.toDate} />
                    </FormGroup>
                    <Button type='submit'>Submit</Button>
                </Form>
            </Container>
        </>
    );

}

export default ExperienceEdit;