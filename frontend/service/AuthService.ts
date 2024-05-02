class AuthService {

    async iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
        const response = await fetch(process.env.BASE_URL! + "/iniciarSesion", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(iniciarSesionDTO)
        })
        const data = await response.json()
        if (!response.ok) {
            throw new Error(data.error)
        }

        localStorage.setItem('token', data.token)
    }
} export default new AuthService()