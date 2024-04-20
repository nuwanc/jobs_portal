import React, { useEffect, useState } from 'react';
import {
    Navbar,
    NavbarBrand,
    NavbarText,
    NavItem,
    NavLink,
    Nav
} from 'reactstrap';
import { Outlet, Link } from "react-router-dom";

function Layout() {
    const [user, setUser] = useState(true);

    useEffect(() => {
        fetch('/api/user')
            .then(response => response.json())
            .then(data => setUser(data))
            .catch(error => console.error('Error fetching user:', error));
    }, []);

    return (
        <>
            {user ?
                <Navbar color="dark" dark={true} expand="md">
                    <NavbarBrand>Star Light Job Portal</NavbarBrand>
                    <Nav className="me-auto" navbar>
                        <NavItem>
                            <NavLink tag={Link} to="/profile.do">Profile</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/education.do">Education</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/skills.do">Skills</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/experience.do">Experience</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/professional.do">Professional</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/resume.do">Resume</NavLink>
                        </NavItem>
                    </Nav>
                    <Nav className="ms-auto" navbar>
                        <NavItem>
                            <NavLink href="/logout">Logout</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
                :
                <Navbar color="dark" dark={true} expand="md">
                    <NavbarBrand>Star Light Job Portal</NavbarBrand>
                    <Nav className="me-auto" navbar>
                        <NavItem>
                            <NavLink tag={Link} to="/profile.do">Profile</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink tag={Link} to="/Search.do">Search</NavLink>
                        </NavItem>
                    </Nav>
                    <Nav className="ms-auto" navbar>
                        <NavItem>
                            <NavLink href="/logout">Logout</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
            }
            <Outlet />
        </>
    );
}

export default Layout;