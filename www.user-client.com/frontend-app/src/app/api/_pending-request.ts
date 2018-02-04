export class PendingRequest {

  public type: string;

  public url: string;

  public data: any;

  constructor(type: string, url: string, data?: any) {
    this.type = type;
    this.url = url.split("?")[0];
    this.data = data ? data : this.storeUrlData(url);
  }

  storeUrlData(url: any): void {
    let params = url.split("?")[1];
    this.data = {};
    while (params) {
      let param;
      if (params.includes("&")) {
        param = params.split("&")[0];
      } else {
        param = params;
      }
      let key = param.split("=")[0];
      let value = param.split("=")[1];
      this.data[key] = value;
      params = params.split("&")[1];
    }
  }

}
