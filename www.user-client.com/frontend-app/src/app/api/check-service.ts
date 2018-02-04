import {Client} from "./_client";
import {AvailabilityResponse} from "../http/response/availability-response";

export class CheckService {

  private client: Client;

  constructor() {
    this.client = Client.GET_INSTANCE();
  }

  checkUsername(username: string): Promise<AvailabilityResponse> {
    return this.client.get("/api/check/username/public?username=" + username);
  }

  checkEmail(email: string): Promise<AvailabilityResponse> {
    return this.client.get("/api/check/email/public?email=" + email);
  }

}
