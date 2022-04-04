import { Router } from "express";

import UserController from "../controller/UserController.js";
import CheckToken from "../../../config/auth/CheckToken.js";

const router = new Router();

router.get('/api/user/email/:email', UserController.findByEmail);
router.use(CheckToken);
router.post('/api/user/auth', UserController.getAccessToken)

export default router;
