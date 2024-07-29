$( () =>{
    window.ctrl = new ValidaTorneo();
    ctrl.init();
});
class ValidaTorneo {
    constructor () {
        this.config = {
            formulario: "form[name=formTorneo]",
            ibFecha: "[name=formTorneo\\:fech]",
            ibNombre: "[name=formTorneo\\:nombre]",
            ibDescripcion: "[name=formTorneo\\:desc]",
        };
    }
    init (){
        console.log("Cargado");
        $(this.config.formulario).on('submit', () => {
            return this.validarDatos()
        });
    }
    validarDatos(){
        console.log("Validar Datos");
        let result = true;
        let nombre = $(this.config.ibNombre).val();
        let fecha = $(this.config.ibFecha).val();
        let descripcion = $(this.config.ibDescripcion).val();

        if (nombre.length < 3 || nombre.length > 20){
            $('#errNombre').text('Error, el nombre debe tener mas de 3 y menos de 20 caracteres');
            result = false;
            $('#errNombre').show();
            console.log("Nombre invalido");
        } else {
            $('#errNombre').hide();
        }

        if (descripcion == ""){
            $('#errDesc').text('Error, la descripcion no puede ir vacia');
            result = false;
            $('#errDesc').show();
            console.log("Descripcion invalida");
        }else{
            $('#errDesc').hide();
        }
        if(fecha<13/5/2022){
            $('#errFechaT').text('Error, la fecha no puede ser mas antigua de hace 1 año');
            result = false;
            $('#errFechaT').show();
            console.log("Fecha Torneo invalida");
        }else {
            $('#errFechaT').hide();
        }


        return result;
    }
}