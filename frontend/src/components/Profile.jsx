import React, { useEffect, useState } from 'react';
import { Container, Button, Form, FormGroup, Label, Input } from 'reactstrap';


function Profile() {
    const [profile, setProfile] = useState({});

    useEffect(() => {
        fetch('/api/profile')
            .then(response => response.json())
            .then(data => setProfile(data))
            .catch(error => console.error('Error fetching profile:', error));
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/profile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(profile),
        })
            .then(response => response.json())
            .then(data => console.log('profile updated:', data))
            .catch(error => console.error('Error updating profile:', error));
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setProfile(prevProfile => ({ ...prevProfile, [name]: value }));
    };

    return (
        <>
            <Container className="bg-light border">
                <h2>Profile</h2>
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" placeholder="Name" onChange={handleChange} value={profile.name} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="age">Age</Label>
                        <Input type="number" name="age" id="age" placeholder="Age" onChange={handleChange} min={1} max={100} value={profile.age} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="handlers">Address</Label>
                        <Input type="text" name="address" id="address" placeholder="Address" onChange={handleChange} value={profile.address} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="handlers">Date of Birth</Label>
                        <Input type="date" name="dob" id="dob" placeholder="DOB" onChange={handleChange} value={profile.dob} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="handlers">Email</Label>
                        <Input type="email" name="email" id="email" placeholder="Email" onChange={handleChange} value={profile.email} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="handlers">Mobile No</Label>
                        <Input type="tel" name="mobile" id="mobile" placeholder="Mobile" onChange={handleChange} value={profile.mobile} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="website">Website</Label>
                        <Input type="url" name="website" id="website" placeholder="Website" onChange={handleChange} value={profile.website} />
                    </FormGroup>
                    <Button type='submit'>Submit</Button>
                </Form>
            </Container>
        </>
    );
}

export default Profile;