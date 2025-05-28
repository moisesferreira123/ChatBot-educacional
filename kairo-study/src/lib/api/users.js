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