<template>

<Menu></Menu>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4" v-for="t in torneos" :key="t.id">
                <div class="card">
                    <img class="card-img-top" alt="Imagen no disponible" src="./assets/images/tenis.jpg"/>
                    <div class="card-body">
                        <h5 class="card-title">{{t.nombre}}</h5>
                        <h6 class="card-subtitle">{{t.fecha}}</h6>
                        <p class="card-text">{{t.descripcion}}</p>
                    </div>

                    <form v-on:submit.prevent='altaInscribe(t.id,this.torneosInscritos)'>
                        <button type="submit" :class="buscaInscripcion(t.id,this.torneosInscritos) ? botonNoInscribe() : botonInscribe()">{{this.contenidoBoton(this.modoBoton)}}</button>
                    </form>


                </div>
            </div>

        </div>

    </div>

</template>

<script>

import {TorneoDAOFetch} from "./services/torneo.service.js";
import {InscribeDAOFetch} from "./services/inscribe.service.js";
import Menu from "./components/comun/Menu.vue"

const torneoDAO = new TorneoDAOFetch('http://localhost:8080/gestionaTennis-1.0-SNAPSHOT/api/torneos');
const inscribeDAO = new InscribeDAOFetch('http://localhost:8080/gestionaTennis-1.0-SNAPSHOT/api/inscribir');
export default {

    components: {
        Menu
    },
    provide: {
        torneoDAO,inscribeDAO
    },
    mounted() {
        this.inscritoConsulta();
        this.cargaTorneos();
        this.cargaInscribe();

    },
    data() {
        return {
            torneos: [ ],
            inscripciones: [ ],
            torneosInscritos: [ ],
            inscripcion: {},
            torneo: {},
            idSocioActual: {},
            errorMsgs: {},
            modoBoton: {}
        }
    },
    methods: {
        cargaTorneos() {
            torneoDAO.buscaTodos()
                .then(data => {
                    this.torneos = data
                    this.torneo = { }
                    this.errorMsgs={ }
                })
        },
        async borraTorneo(torneoId) {
            if (torneoId > 0) {
                await torneoDAO.borra(torneoId);
                this.cargaTorneos();
            }
        },
        async visualizaTorneo(torneoId) {
            console.log(`visualizando cliente ${torneoId}`)
            this.torneo = await torneoDAO.busca(torneoId)
        },
        altaTorneo() {
            this.errorMsgs={ }
            this.torneo = {id: 0, nombre: '', descripcion: '', fecha: '1111/11/11'}
        },
        async torneoActualizado() {
            console.log("actualizado torneo en servidor")
            this.cargaTorneos()
        },

        //Listado de metodos de inscribe
        cargaInscribe() {
            inscribeDAO.buscaTodos()
                .then(data => {
                    this.inscripciones = data
                    this.inscripcion = { }
                    this.errorMsgs={ }
                });

            inscribeDAO.dameInsIdSocio(this.idSocioActual).then(datav => {
                this.torneosInscritos = datav
                this.errorMsgs={ }
            })
        },

        async borraInscripcion(insId) {
            if (insId > 0) {
                await inscribeDAO.borra(insId);
                this.cargaInscribe();
            }
        },

        async visualizaInscripcion(insId) {
            console.log(`visualizando inscripcion ${insId}`)
            this.inscripcion = await inscribeDAO.busca(insId)
        },

        async altaInscribe(idTorneo,lista) {
            this.errorMsgs={ }

            try {
                    if (idTorneo > 0 && this.idSocioActual > 0 && !this.buscaInscripcion(idTorneo,lista)){
                        this.inscripcion = {id:0,idTorneo:idTorneo,idSocio:this.idSocioActual}
                        await inscribeDAO.crea(this.inscripcion)
                        console.log("Inscripcion creada")
                        this.cargaInscribe()
                    }else {
                        this.inscripcion = {id:0,idTorneo:idTorneo,idSocio:this.idSocioActual}
                        let borra = await inscribeDAO.dameMiInscripcion(this.inscripcion.idSocio,this.inscripcion.idTorneo);
                        if (await inscribeDAO.borra(borra.id)){
                            console.log("Inscripcion Borrada")
                            this.cargaInscribe()
                        }//if
                    }//else


                }catch (err){
                    console.log("Errores de validación en servidor"+err)
            }



        },
        async inscribeActualizado() {
            console.log("actualizado inscripcion en servidor")
            this.cargaInscribe()
        },

        inscritoConsulta() {
            this.idSocioActual=0;
            const query = window.location.search;
            this.idSocioActual = query;

            const params = new URLSearchParams(query);

            this.idSocioActual =  params.get('id');

        },

        buscaInscripcion(idTorneo,lista){
            let encontrado=false;

            for (let i=0; i< lista.length;i++){
                let tor = lista.at(i);
                if (idTorneo === tor.id){
                    encontrado = true;
                    break;
                }
            }

            return  encontrado;
        },

        botonInscribe(){
            this.modoBoton = true;
            return 'btn btn-primary'
        },
        botonNoInscribe(){
            this.modoBoton = false;
            return 'btn btn-danger'
        },
        contenidoBoton(modo){

            if (modo){
                return "Inscribir"
            }else {
                return "Cancelar Inscripción"
            }

        }


    }
}

</script>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
}

h3 {
    font-size: 32px;
    margin-top: 40px;
    margin-bottom: 20px;
}

p {
    font-size: 15px;
    line-height: 1.75;
    color: rgb(0, 0, 0);
    margin-bottom: 20px;
}

img{
    width: 30em;
    height: 20em;
}


</style>
