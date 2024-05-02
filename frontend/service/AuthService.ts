class AuthService {

    async iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
        const response = await fetch(process.env.BACKEND_URL!, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(iniciarSesionDTO)
        })
        const jwt = await response.json()
        localStorage.setItem('jwt', jwt)
    }

} export default new AuthService()