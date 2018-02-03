import {LoadTypes} from "./load-types.enum";
import {AppUserConfirmation} from "../app-user-confirmation/app-user-confirmation";
import {AppUserPasswordReset} from "../app-user-password-reset/app-user-password-reset";

export class AppUser {

  public id: number;
  public username: string;
  public email: string;
  public password: string;
  public confirmed: boolean;
  public active: boolean;

  public appUserConfirmation: AppUserConfirmation;

  public appUserPasswordResets: AppUserPasswordReset[];

  constructor(){

  }

  public load(loadType?: LoadTypes): AppUser {
    const loaded = new AppUser();
    switch (loadType) {
      case LoadTypes.FULL:
        return this;
      case LoadTypes.CORE:
        loaded.id = this.id;
        loaded.username = this.username;
        loaded.email = this.email;
        loaded.password = this.password;
        loaded.active = this.active;
        loaded.confirmed = this.confirmed;
      default:
        return this;
    }
  }

}
