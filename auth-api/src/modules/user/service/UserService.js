import UserRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import UserException from "../exception/UserException.js";

class UserService {

    async findByEmail(req){
        try {
            const { email } = req.params;
            this.validarDadosRequisicao(email);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user); 
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
}

export default new UserService;