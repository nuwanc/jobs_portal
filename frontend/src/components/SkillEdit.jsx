import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Container, Toast, ToastBody, ToastHeader } from 'reactstrap';

function SkillEdit() {
    const [skill, setSkill] = useState({ skillRating: 0 });
    const [visible, setVisible] = useState(false);
    const toggle = () => setVisible(!visible);

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/skill', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(skill),
        })
            //.then(response => response.json())
            //.then((data) => setSkill({}))
            .then(() => {
                setVisible(true);
                setSkill({ skillName: '', noOfYears: 0, skillRating: '0' });
            })
            .catch(error => console.error('Error creating skill:', error));

    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setSkill(prevSkill => ({ ...prevSkill, [name]: value }));
    };


    return (
        <>
            <Container className="bg-light border">
                <h2>Add Skill</h2>
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="skillName">Skill</Label>
                        <Input type="text" name="skillName" id="skillName" placeholder="skill" onChange={handleChange} value={skill.skillName} required />
                    </FormGroup>
                    <FormGroup>
                        <Label for="noOfYears">Years</Label>
                        <Input type="number" name="noOfYears" id="noOfYears" placeholder="age" onChange={handleChange} min={1} max={100} value={skill.noOfYears} required />
                    </FormGroup>
                    <FormGroup>
                        <Label for="skillRating">Skill Rating</Label>
                        <select onChange={handleChange} value={skill.skillRating} name="skillRating" id="skillRating">
                            <option value="0">Beginner</option>
                            <option value="1">Novice</option>
                            <option value="2">Intermediate</option>
                            <option value="3">Advanced</option>
                            <option value="4">Expert</option>
                        </select>
                    </FormGroup>
                    <Button type='submit'>Submit</Button>
                </Form>
            </Container>
            <div style={{
                position: 'fixed',
                top: 20,
                right: 20,
                zIndex: 1050,
            }}>            <Toast isOpen={visible}>
                    <ToastHeader toggle={toggle}>Add Skill</ToastHeader>
                    <ToastBody>Skill added successfuly.</ToastBody>
                </Toast>
            </div >
        </>
    );
}

export default SkillEdit;