$( () =>{
    window.ctrl = new ValidaSocio();
    ctrl.init();
});
class ValidaSocio {
    constructor () {
        this.config = {
            formulario: "form[name=fRegistra]",
            ibNombre: "[name=fRegistra\\:idNombre]",
            ibApellidos: "[name=fRegistra\\:idApellidos]",
            ibDNI: "[name=fRegistra\\:idDni]",
            ibTelefono: "[name=fRegistra\\:idTelefono]",
            ibDireccion: "[name=fRegistra\\:direccion]",
            ibFechaNacimiento: "[name=fRegistra\\:idFNac]",
            ibEmail: "[name=fRegistra\\:email]",
            ibNivel: "[name=fRegistra\\:idnivel]",
            ibPass: "[name=fRegistra\\:pass]"
        };
    }
    init (){
        console.log("Cargado");
        $(this.config.formulario).on('submit', () => {
                return this.validarDatos()
            });
        $(this.config.ibNombre).focus();
    }
    validarDatos(){
        console.log("Validar Datos");
        let result = true;
        let nombre = $(this.config.ibNombre).val();
        let apellidos = $(this.config.ibApellidos).val();
        let dni = $(this.config.ibDNI).val();
        let direccion = $(this.config.ibDireccion).val();
        let telefono = $(this.config.ibTelefono).val();
        let fecha = $(this.config.ibFechaNacimiento).val();
        let email = $(this.config.ibEmail).val();
        let nivel = $(this.config.ibNivel).val();
        let pass = $(this.config.ibPass).val();

        if (nombre.length < 3 || nombre.length > 20){
            $('#errNombre').text('Error, el nombre debe tener mas de 3 y menos de 20 caracteres');
            result = false;
            $('#errNombre').show();
            console.log("Nombre invalido");
        }else {
            $('#errNombre').hide();
        }

        if (direccion.length < 3 || direccion.length > 40){
            $('#errDireccion').text('Error, el nombre debe tener mas de 3 y menos de 40 caracteres');
            result = false;
            $('#errDireccion').show();
            console.log("Nombre invalido");
        }else {
            $('#errDireccion').hide();
        }

        if (nivel < 1 || nivel > 6){
            $('#errNivel').text('El nivel debe estar comprendido entre 1 y 6');
            result=false;
            $('#errNivel').show();
            console.log("Nivel invalido");
        }else {
            $('#errNivel').hide();
        }


        if(apellidos.length < 3 || apellidos.length > 20){
            $('#errApellidos').text('Error, los apellidos deben tener mas de 3 y menos de 20 caracteres');
            result = false;
            $('#errApellidos').show();
            console.log("Apellidos invalidos");
        }else {
            $('#errApellidos').hide();
        }


        if(dni.length!=9){
            $('#errDNI').text('Error, no tiene longitud 9');
            result = false;
            $('#errDNI').show();
            console.log("DNI no valido");
        }else {
            $('#errDNI').hide();
        }

        if(telefono==""){
            $('#errTlf').text('Error, el telefono esta vaci0');
            result = false;
            $('#errTlf').show();
            console.log("Telefono invalido");
        }else {
            $('#errTlf').hide();
        }

        if(fecha<14/12/1923){
            $('#errFecha').text('Error, la fecha es anterior a 1923');
            result = false;
            $('#errFecha').show();
            console.log("Fecha invalida");
        }else {
            $('#errFecha').hide();
        }


        if(email==""){
            $('#errEmail').text('Error, el email esta vacia');
            result = false;
            $('#errEmail').show();
            console.log("Email invalido");
        }else {
            $('#errEmail').hide();
        }

        if (pass == ""){
            $('#errPass').text('Error, la constraseña esta vacia');
            result = false;
            $('#errPass').show();
            console.log("Contraseña invalida");
        }else {
            $('#errPass').hide();
        }
        return result;
    }
}