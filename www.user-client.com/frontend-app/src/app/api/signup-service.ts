import {Client} from "./_client";
import {AppUser} from "../model/app-user-auth/app-user/app-user";

export class SignupService {

  private client: Client;

  private signupUrl: string;

  constructor() {
    this.client = Client.GET_INSTANCE();
    this.signupUrl = "/api/signup/public";
  }

  signup(appUser: AppUser) {
    this.client.post(this.signupUrl, appUser);
  }

  signingUp() {
    return this.client.observeBaseURL(this.signupUrl);
  }

}
