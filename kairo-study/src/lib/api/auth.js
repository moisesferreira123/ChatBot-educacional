export async function fetchAuthRegister(fullName,email,password) {
	const response = await fetch('http://localhost:8080/auth/register', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			fullName: fullName,
			email: email,
			password: password
		})
	});

	if (response.ok) {
		const data = await response.json();
		return data.token;
	} else {
		const errorMessage = await response.json();
		throw new Error(errorMessage.message);
	}
}

export async function fetchAuthLogin(email, password) {
	const response = await fetch('http://localhost:8080/auth/login', {
			method: 'POST',
			headers: {
					'Content-Type': 'application/json'
			},
			body: JSON.stringify({
					email: email,
					password: password
			})
	});

	const responseData = await response.json().catch(() => null);
	console.log(response);
	if (!response.ok) {
			// Se tivermos dados de erro do back-end, use a mensagem deles
			if (responseData && responseData.message) {
					throw new Error(responseData.message);
			}
			// Caso contrário, use o status HTTP
			throw new Error(`Authentication failed with status ${response.status}`);
	}

	return responseData.token;
}
