<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Login Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header text-center">
                    <h4>Login Admin</h4>
                </div>
                <div class="card-body">
                    <form action="proses_login.php" method="POST">
                        <div class="mb-3">
                            <label>Username</label>
                            <input type="text" name="username" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>CAPTCHA</label><br>
                            <img src="captcha.php" alt="captcha" class="mb-2">
                            <input type="text" name="captcha" class="form-control" placeholder="Masukkan CAPTCHA" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Login</button>
                        <hr>
                        <div class="mt-3">
                            <a href="../index.php" class="btn btn-outline-secondary w-100 rounded-pill">
                            <i class="bi bi-shop me-2"></i> Belanja Lagi
                             </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>