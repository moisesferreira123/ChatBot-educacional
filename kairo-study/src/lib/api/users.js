export async function fetchGetUserById(token) {
  const response = await fetch(`http://localhost:8080/api/users`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error getting user by Id");
  return response.json();
} 

export async function fetchUpdateUserPersonalInformations(fullName, username, email, userId, token) {
  const response = await fetch(`http://localhost:8080/api/users/update-user-informations/${userId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      fullName: fullName,
      username: username,
      email: email
    })
  });
  if(!response.ok) throw new Error("Error updating personal informations user");
  return response.json();
}

export async function fetchUpdateUserPassword(currentPassword, newPassword, confirmNewPassword, userId, token) {
  const response = await fetch(`http://localhost:8080/api/users/update-user-password/${userId}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      currentPassword: currentPassword,
      newPassword: newPassword,
      confirmNewPassword: confirmNewPassword
    })
  });
  if(!response.ok) throw new Error("Error updating password user");
}

export async function fetchDeleteUser(token) {
  const response = await fetch(`http://localhost:8080/api/users`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok) throw new Error("Error deleting user");
}