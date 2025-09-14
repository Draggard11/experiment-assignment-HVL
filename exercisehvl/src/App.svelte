<script>
    let username = "";
    let email = "";

    function handleSubmit() {
        if (!username || !email) {
            alert("Please fill in both fields.");
            return;
        }
        fetch("/users", {
            method: "POST",
            body: JSON.stringify({
                username: username,
                email: email
            }),
            headers: {
                "Content-Type": "application/json"
            },
        }).then((response) => {
            if (response.status === 201) {
                alert("User information submitted successfully!");
            } else {
                alert("Failed to submit user information.");
            }
        }).catch((error) => {
            console.error("Error:", error);
            alert("An error occurred while submitting user information.");
        });
    }
</script>

<div>
    <div>
        <label for="username">Username:</label>
        <input id="username" type="text" bind:value={username} placeholder="Enter your username" />
    </div>

    <div>
        <label for="email">Email:</label>
        <input id="email" type="email" bind:value={email} placeholder="Enter your email" />
    </div>

    <button on:click={handleSubmit}>Submit</button>

    <h2>Your Information</h2>
    <p><strong>Username:</strong> {username}</p>
    <p><strong>Email:</strong> {email}</p>
</div>