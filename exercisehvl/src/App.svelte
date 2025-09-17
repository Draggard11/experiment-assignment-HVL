<script>
    import {onMount} from "svelte";

    let username = $state();
    let email = $state();

    let users = $state(fetch("http://localhost:8080/users").then((response) => {
        return response.json();
    }))

    let polls = $state(fetch("http://localhost:8080/polls").then((response) => {
        return response.json();
    }))

    function handleSubmit() {
        if (!username || !email) {
            alert("Please fill in both fields.");
            return;
        }
        fetch("http://localhost:8080/users", {
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
            alert(error);
        });
    }

    // The Svelte framework methods onMount can be used to execute code after the component has been rendered.
    onMount(() => {
        // here we use it register a background task (using JS setInterval() function) that every 5 sec (5000 ms)
        // refreshes the weather location data by calling the GET request again
        const intervalId1 = setInterval(() => {
            users = fetch("http://localhost:8080/users").then((response) => response.json());
            polls = fetch("http://localhost:8080/polls").then((response) => response.json());
        }, 5000);

        // the onMount function can return a callback function that "clears up" after a component has been removed.
        // in this case, the background task should be removed by calling clearInterval()
        return () => clearInterval(intervalId1);
    })
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

    <!-- svelte-ignore block_empty -->
    {#await users}

    {:then user}
        <h2>users:</h2>
        <h2>{user}</h2>

    {:catch error}
        {error}
    {/await}

    {#await polls}
        waiting
    {:then ready}
        <table>
            <thead>
            <tr>
                <th>owner</th>
                <th>question</th>
                <th>options</th>
            </tr>
            </thead>
        </table>
        {#each ready as options}
            <tr>
                <td>options.caption}</td>
                <td>options.votes</td>
            </tr>
        {/each}
    {/await}
</div>