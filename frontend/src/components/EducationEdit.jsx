import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Container, Toast, ToastBody, ToastHeader } from 'reactstrap';

function EducationEdit() {
    const [education, setEducation] = useState({});
    
    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/education', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(education),
        })
           // .then(response => response.json())
          //  .then((data) => setExperience({}))
            .catch(error => console.error('Error creating education:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setEducation(prevEducation => ({ ...prevEducation, [name]: value }));
    };
    return(
        <>
            <Container className="bg-light border">
                <h2>Add Education</h2>
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="institute">Institute</Label>
                        <Input type="text" name="institute" id="institute" placeholder="institute" onChange={handleChange} value={education.institute} required />
                    </FormGroup>
                    <FormGroup>
                        <Label for="institute">Degree</Label>
                        <Input type="text" name="degree" id="degree" placeholder="degree" onChange={handleChange} value={education.degree} required />
                    </FormGroup>
                    <FormGroup>
                        <Label for="level">Level</Label>
                        <select onChange={handleChange} value={education.level} name="level" id="level">
                            <option value="0">GCSE</option>
                            <option value="1">GCSE (OL)</option>
                            <option value="2">GCSE (AL)</option>
                            <option value="3">Associate</option>
                            <option value="4">Bachelors</option>
                            <option value="5">Masters</option>
                            <option value="6">Phd.</option>
                        </select>
                    </FormGroup>
                    <FormGroup>
                        <Label for="institute">Field</Label>
                        <Input type="text" name="field" id="field" placeholder="field" onChange={handleChange} value={education.field}  />
                    </FormGroup>
                    <FormGroup>
                        <Label for="fromDate">Start Date</Label>
                        <Input type="date" name="startDate" id="startDate" placeholder="start date" onChange={handleChange} value={education.startDate} required/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="toDate">End Date</Label>
                        <Input type="date" name="endDate" id="endDate" placeholder="end date" onChange={handleChange} value={education.endDate} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="toDate">No. Of Passes</Label>
                        <Input type="number" name="passes" id="passes" placeholder="no. of subjects passed" onChange={handleChange} value={education.passes} />
                    </FormGroup>
                    <Button type='submit'>Submit</Button>
                </Form>
            </Container>
        </>
    )
}
export default EducationEdit;