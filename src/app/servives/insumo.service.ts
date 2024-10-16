import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InsumoService {

  private baseUrl = environment.apiUrl;

  private insumos: string =`${this.baseUrl}/insumos`

  constructor(private http: HttpClient) 
  { }

listarInsumos():Observable<any>{
 return this.http.get(this.insumos) 
}

eliminarInsumos(id:number):Observable<any>{
  return this.http.delete(`${this.insumos}/${id}`)
}
 
generarInsumos(formulario: any):Observable<any>
{
  return this.http.post(`${this.insumos}`, formulario)
}

obtenerInsumo(id:number): Observable<any>{

  return this.http.get(`${this.insumos}/${id}`)
}

editarInsumo(id: number, request: any) : Observable<any>
{
  return this.http.put(`${this.insumos}/${id}`, request)
}



}

