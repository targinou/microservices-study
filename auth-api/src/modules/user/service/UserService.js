import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

import UserRepository from "../repository/UserRepository.js";
import UserException from "../exception/UserException.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import * as secrets from "../../../config/constants/secrets.js"


class UserService {

    async findByEmail(req){
        try {
            const { email } = req.params;
            const { authUser } = req;
            this.validarDadosRequisicao(email);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user); 
            this.validateAuthenticatedUser(user, authUser);
            return {
                status: httpStatus.SUCESS,
                user: {
                    id: user.id,
                    id: user.name,
                    id: user.email,
                },
            };
        } catch (err) {
            return {
                status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message,
            };
        }

    }

    validateRequestData(email){

        if (!email) {
            throw new UserException(httpStatus.BAD_REQUEST, "O usuário não informou email.")
        }
    }

    validateUserNotFound(user) {

        if(!user) {
            throw new Error(httpStatus.BAD_REQUEST, "Usuário não foi informado!")
        }
    }

    validateAuthenticatedUser(user, authUser) {
        if(!authUser || user.id !== authUser.id){
            throw new UserException(
                httpStatus.FORBIDDEN,
                "You cannot see this user data."
            );
        }
    }

    async getAccessToken(req){
        try {
            const {email, password} = req.body;
            this.validateAccessTokenData(email, password);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);
            await this.validatePassword(password, user.password);
            const authUser = {id: user.id, name: user.name, email: user.email};
            const accessToken = jwt.sign({authUser}, secrets.API_SECRET, {expiresIn: '1d'});
            return {
                status: httpStatus.SUCESS,
                accessToken,
            }
        }   catch (err) {
                return {
                    status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
                    message: err.message,
                };
            }
    }

    validateAccessTokenData(email, password) {
        if(!email || !password) {
            throw new UserException (
                httpStatus.UNAUTHORIZED,
                "Email and password must be informed."
            )
        }
    }

    async validatePassword(password, hashPassword){
        if(!await bcrypt.compare(password, hashPassword)){
            throw new UserException(httpStatus.UNAUTHORIZED, "Password doesn't match.");
        }
    }
}

export default new UserService;