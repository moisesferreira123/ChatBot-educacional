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
		alert('Error registering user. Email already registered.');
		console.error(await response.text());
		return '';
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

	if (response.ok) {
		const data = await response.json();
		return data.token;
	} else {
		alert('Invalid email or password');
        console.error(await response.text());
        return '';
	}
}
