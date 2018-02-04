import {Client} from "./_client";
import {SuccessResponse} from "../http/response/success-response";
import {LoginRequest} from "../http/request/login-request";

export class LoginService {

  private client: Client;

  private loginUrl: string;

  constructor(){
    this.client = Client.GET_INSTANCE();
    this.loginUrl = "/api/login/public";
  }

  login(loginRequest: LoginRequest): Promise<SuccessResponse> {
    return this.client.post(this.loginUrl, loginRequest);
  }

  loggingIn(): boolean {
    return this.client.observeBaseURL(this.loginUrl);
  }

}
