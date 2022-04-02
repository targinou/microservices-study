import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "postgres", "123456", {
    host: "localhost",
    dialect: "postgres",
    quoteIdentifiers: false,
    define: {
        syncOnAssociation: true,
        timestamps: false,
        undescored: true,
        undescoredAll: true,
        freezeTableName: true,
    },
});

sequelize.authenticate().then(() => {
    console.info("A conexão foi estabelecida!");
})
.catch((err) => {
    console.error("Não foi possível conectar ao banco de dados.");
    console.error(err.message);
});

export default sequelize;