import React, { useEffect, useState } from 'react';

function UsersList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch('/api/users')
      .then(response => response.json())
      .then(data => setUsers(data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

  return (
    <div>
      <h2>Users</h2>
      <ul>
        {users.map(user => (
          <li>{user.username} / {user.role}</li>
        ))}
      </ul>
    </div>
  );
}

export default UsersList;