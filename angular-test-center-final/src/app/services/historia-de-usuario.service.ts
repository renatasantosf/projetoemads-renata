import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { HistoriaDeUsuario } from '../classes/historia-de-usuario';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class HistoriaDeUsuarioService {

  HISTORIA_URL = 'http://localhost:8081/historia/';
  constructor(private http: HttpClient) { }

  getHistoriaById(id:number) {
    return this.http.get<HistoriaDeUsuario>(this.HISTORIA_URL+id);
  }

  saveHistoria(historia) : Observable<HistoriaDeUsuario> {
    return this.http.post<HistoriaDeUsuario>(this.HISTORIA_URL,historia,httpOptions);
  }

  updateHistoria(id:number,historia): Observable<any> {
    return this.http.put<HistoriaDeUsuario>(this.HISTORIA_URL+id,historia,httpOptions)
  }

  deleteHistoria(id:number) {
    return this.http.delete<HistoriaDeUsuario>(this.HISTORIA_URL+id);
  }

  getAllHistorias(){
    return this.http.get<HistoriaDeUsuario[]>(this.HISTORIA_URL);
  }

  getHistoriaByProjeto(id:number) {
    return this.http.get<HistoriaDeUsuario[]>(this.HISTORIA_URL+"byprojeto/"+id);
  }

  getHistoriaDeUsuarioPorNome(id:number,nome:string) {
    return this.http.get<HistoriaDeUsuario[]>(this.HISTORIA_URL+id+"/"+nome);
  }
}
