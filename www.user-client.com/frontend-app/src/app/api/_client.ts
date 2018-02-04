import {PendingRequest} from "./_pending-request";

export class Client {

  private urlPrefix: string;
  private pendingRequests: PendingRequest[];

  private static INSTANCE: Client;
  static GET_INSTANCE(): Client {
    Client.INSTANCE = Client.INSTANCE || new Client();
    return this.INSTANCE;
  }

  private constructor(){
    this.urlPrefix = "localhost:8000";
    this.pendingRequests = [];
  }

  get(url){
    const pending = new PendingRequest("GET", url)
    this.report(pending);
    return new Promise((resolve, reject) => {
      const xhttp = this.createXMLHttpRequest();
      xhttp.onreadystatechange = () => this.handleResponse(resolve, reject, xhttp, pending);
      xhttp.open("GET", this.urlPrefix + url, true);
      xhttp.send();
    })
  }

  post(url, data){
    const pending = new PendingRequest("POST", url, data);
    this.report(pending);
    return new Promise((resolve, reject) => {
      const xhttp = this.createXMLHttpRequest();
      xhttp.onreadystatechange = () => this.handleResponse(resolve, reject, xhttp, pending);
      xhttp.open("POST", this.urlPrefix + url, true);
      xhttp.send(JSON.stringify(data));
    })
  }

  observeBaseURL(url: string): boolean {
    return this.pendingRequests.find(
      (entry: PendingRequest) => entry.url == url
    ) != null;
  }

  observeUrlWithParams(url: string): boolean {
    let request = new PendingRequest("GET", url);
    return true;
  }

  observeData(): boolean {
    return true;
  }

  private createXMLHttpRequest(){
    const xhttp = new XMLHttpRequest();
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    this.authorize(xhttp);
    return xhttp;
  }

  private authorize(xhttp){
    const token = localStorage.get("auth-token");
    xhttp.setRequestHeader("Authorization", token);
  }

  private handleResponse(resolve, reject, xhttp, pendingRequest: PendingRequest){
    if (xhttp.readyState == 4) {
      if (xhttp.status == 200) { resolve(JSON.parse(xhttp.responseText)); }
      else { reject(xhttp.status); }
      this.complete(pendingRequest);
    }
  }

  private report(pendingRequest: PendingRequest) {
    this.pendingRequests.push(pendingRequest);
  }

  private complete(pendingRequest: PendingRequest) {
    this.pendingRequests = this.pendingRequests.filter((entry: PendingRequest) => {
      if (entry.type != pendingRequest.type) {
        return true;
      }
      if (entry.url != pendingRequest.url) {
        return true;
      }
      for (let key in Object.keys(entry.data)) {
        if (entry.data[key] != pendingRequest.data[key]) {
          return true;
        }
      }
      return false;
    });
  }

}
