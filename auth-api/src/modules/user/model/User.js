import Sequelize from "sequelize";

import sequelize from "../../../config/db/dbConfig.js";

const User = sequelize.define(
    "user", 
    {
        id: {
            type: Sequelize.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        name: {
            type: Sequelize.STRING,
            alloyNull: false,
        },
        email: {
            type: Sequelize.STRING,
            alloyNull: false,
        },
        password: {
            type: Sequelize.STRING,
            alloyNull: false,
        }
    },
    {}
);

export default User;