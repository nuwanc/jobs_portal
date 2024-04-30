import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input, Container, Table } from 'reactstrap';
import {
    Accordion,
    AccordionBody,
    AccordionHeader,
    AccordionItem,
} from 'reactstrap';
import { Link } from 'react-router-dom';

function Search() {
    const [search, setSearch] = useState({
        'searchField': 'education',
        'levelOp': '1',
        'level': '1',
        'passesOp': '0',
        'passes': '0',
        'skillOp': '0',
        'skillYears': '0',
        'expOp': '0',
        'expYears':'0'
    });
    const [profiles, setProfiles] = useState([]);

    const [open, setOpen] = useState('1');
    const toggle = (id) => {
        if (open === id) {
            setOpen();
        } else {
            setOpen(id);
        }
        switch (id) {
            case "1":
                setSearch({ ...search, searchField: 'education' })
                break;
            case "2":
                setSearch({ ...search, searchField: 'skill' })
                break;
            case "3":
                setSearch({ ...search, searchField: 'experience' })
                break;
            case "4":
                setSearch({ ...search, searchField: 'professional' })
                break;
        }

    };


    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/search', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(search),
        })
            .then(response => response.json())
            .then(data => setProfiles(data))
            .catch((error) => console.error('Error:', error));

    };

    const profileList = profiles.map(profile => {
        return <tr key={profile.id}>
            <td scope="row">{profile.name}</td>
            <td>{profile.email}</td>
            <td>{profile.mobile}</td>
            <td>{profile.address}</td>
            <td><Button color="success" tag={Link} to={'/resumes.do/'+profile.id}>Resume</Button></td>
        </tr>
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setSearch(prevSearch => ({ ...prevSearch, [name]: value }));
    };

    return (
        <>
            <Container className="bg-light border">
                <Form onSubmit={handleSubmit}>
                    <Accordion open={open} toggle={toggle}>
                        <AccordionItem>
                            <AccordionHeader targetId="1">Education</AccordionHeader>
                            <AccordionBody accordionId="1">
                                <FormGroup>
                                    <Label for="education">Eduction:</Label>
                                    <Input type="select" name="levelOp" id="levelOp" onChange={handleChange} value={search.levelOp}>
                                        <option value="1">Equal</option>
                                        <option value="2">Minimum</option>
                                    </Input>
                                    <Input type="select" name="level" id="level" onChange={handleChange} value={search.level}>
                                        <option value="0">GCSE</option>
                                        <option value="1">GCSE (OL)</option>
                                        <option value="2">GCSE (AL)</option>
                                        <option value="3">Associate</option>
                                        <option value="4">Bachelors</option>
                                        <option value="5">Masters</option>
                                        <option value="6">Phd.</option>
                                    </Input>
                                </FormGroup>
                                <FormGroup>
                                    <Label for="education">Passes:</Label>
                                    <Input type="select" name="passesOp" id="passesOp" onChange={handleChange} value={search.passesOp}>
                                        <option value="0"></option>
                                        <option value="1">Equal</option>
                                        <option value="2">Minimum</option>
                                    </Input>
                                    <Input type="select" name="passes" id="passes" onChange={handleChange} value={search.passes}>
                                        <option value="0"></option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </Input>
                                </FormGroup>
                            </AccordionBody>
                        </AccordionItem>
                        <AccordionItem>
                            <AccordionHeader targetId="2">Skills</AccordionHeader>
                            <AccordionBody accordionId="2">
                                <FormGroup>
                                    <Label for="skill">Skill:</Label>
                                    <Input type="text" name="skillName" id="skillName" placeholder="skill" onChange={handleChange} value={search.skillName} />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="skillYears">Years:</Label>
                                    <Input type="select" name="skillOp" id="skillOp" onChange={handleChange} value={search.skillOp}>
                                        <option value="0"></option>
                                        <option value="1">Equal</option>
                                        <option value="2">Minimum</option>
                                    </Input>
                                    <Input type="select" name="skillYears" id="skillYears" onChange={handleChange} value={search.skillYears}>
                                        <option value="0"></option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </Input>
                                </FormGroup>
                            </AccordionBody>
                        </AccordionItem>
                        <AccordionItem>
                            <AccordionHeader targetId="3">Experience</AccordionHeader>
                            <AccordionBody accordionId="3">
                                <FormGroup>
                                    <Label for="experience">Experience:</Label>
                                    <Input type="text" name="jobTitle" id="jobTitle" placeholder="title" onChange={handleChange} value={search.jobTitle} />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="education">Years:</Label>
                                    <Input type="select" name="expOp" id="expOp" onChange={handleChange} value={search.expOp}>
                                        <option value="0"></option>
                                        <option value="1">Equal</option>
                                        <option value="2">Minimum</option>
                                    </Input>
                                    <Input type="select" name="expYears" id="expYears" onChange={handleChange} value={search.expYears}>
                                        <option value="0"></option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </Input>
                                </FormGroup>
                            </AccordionBody>
                        </AccordionItem>
                        <AccordionItem>
                            <AccordionHeader targetId="4">Professional</AccordionHeader>
                            <AccordionBody accordionId="4">
                                Professional Go here.
                            </AccordionBody>
                        </AccordionItem>
                    </Accordion>
                    <Button type='submit'>Search</Button>
                </Form>
            </Container>
            <Container className="bg-light border">
                <h2>Profiles</h2>
                <Table bordered>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Address</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {profileList}
                    </tbody>
                </Table>

            </Container>
        </>
    )

}

export default Search;