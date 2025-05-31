export async function fetchGetUserById(token) {
  const response = await fetch(`http://localhost:8080/api/users`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
} 

export async function fetchUpdateUserPersonalInformations(fullName, username, email, token) {
  const response = await fetch(`http://localhost:8080/api/users/update-user-informations`, {
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
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
  return response.json();
}

export async function fetchUpdateUserPassword(currentPassword, newPassword, confirmNewPassword, token) {
  const response = await fetch(`http://localhost:8080/api/users/update-user-password`, {
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
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
}

export async function fetchDeleteUser(token) {
  const response = await fetch(`http://localhost:8080/api/users`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  if(!response.ok){
    const errorMessage = await response.json();
    throw new Error(errorMessage.message);
  }
}