export class InscribeDAOFetch {
    constructor(apiurl) {
        this.srvUrl = apiurl;
        this.respuestaValida=false; //status of last ajax request
    }
    buscaTodos() {
        return fetch(this.srvUrl)
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }
    busca(id = 0) {
        return fetch(this.srvUrl + "/" + id)
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }
    crea(inscribir) {
        return fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(inscribir),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );


    }
    guarda(inscribe) {
        return fetch(`${this.srvUrl}/${inscribe.id}`, {
            method: 'PUT',
            body: JSON.stringify(inscribe),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }
    borra(id = 0) {
        return fetch(this.srvUrl + "/" + id,{
            method: 'DELETE'
        })
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }

    dameInsIdSocio(id = 0){
        return fetch(this.srvUrl + "/socio/" + id)
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }

    dameMiInscripcion(idS=0,idT=0){
        return fetch(this.srvUrl + "/dameI/" + idS + "/" + idT)
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }


    /** Saves response status and returns object data*/
    comprobarRespuesta(response) {
        this.respuestaValida=response.ok;
        return response.json();
    }
    devolverRespuesta (json) {
        if (!this.respuestaValida) {
            return Promise.reject(json);
        }
        return json;
    }
}