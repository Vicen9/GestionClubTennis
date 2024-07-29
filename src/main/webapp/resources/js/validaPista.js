$( () =>{
    window.ctrl = new ValidaPista();
    ctrl.init();
});
class ValidaPista {
    constructor () {
        this.config = {
            formulario: "form[name=formPista]",
            ibFecha: "[name=formPista\\:fecha]",
            ibHora: "[name=formPista\\:hora]",
            ibTipo: "[name=formPista\\:tipo]",

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
        let tipo = $(this.config.ibTipo).val();
        let fecha = $(this.config.ibFecha).val();
        let hora = $(this.config.ibHora).val();

        if (tipo==""){
            $('#errTipo').text('Error, el tipo esta vacio');
            result = false;
            $('#errTipo').show()
            console.log("Tipo invalido");
        }else {
            $('#errTipo').hide();
        }

        if(fecha<13/5/2022){
            $('#errFecha').text('Error, no se pueden escoger reservas anteriores al año pasado');
            result = false;
            $('#errFecha').show();
            console.log("Fecha invalida");
        }else {
            $('#errFecha').hide();
        }

        return result;
    }
}