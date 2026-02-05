const strategies = ["Random", "Greedy", "Smart", "Strategic", "Aggressive"];
let stats = {};
let gameHistory = [];
let totalGames = 0;
let totalDraws = 0;

// Initialize win/draw tracking for each strategy
strategies.forEach(strategy => {
    stats[strategy] = { wins: 0, draws: 0 };
});

// Pick a random strategy
function getRandomStrategy() {
    return strategies[Math.floor(Math.random() * strategies.length)];
}

// Main function to simulate a single game
function simulateGame() {
    const p1 = getRandomStrategy();
    let p2;
    do {
        p2 = getRandomStrategy();
    } while (p1 === p2); // Ensure both players use different strategies

    const totalTurns = Math.floor(Math.random() * 50) + 1;
    const moveLog = [];
    let p1Hits = 0, p2Hits = 0;
    let p1ShipsLeft = 5, p2ShipsLeft = 5;

    // Simulate the turns
    for (let t = 0; t < totalTurns; t++) {
        const row = Math.floor(Math.random() * 10);
        const col = Math.floor(Math.random() * 10);
        const isHit = Math.random() < 0.3;
        const turn = t % 2 === 0 ? "p1" : "p2";

        moveLog.push({ row, col, result: isHit ? "HIT" : "MISS" });

        if (isHit) {
            if (turn === "p1" && p2ShipsLeft > 0) {
                p1Hits++;
                p2ShipsLeft--;
            } else if (turn === "p2" && p1ShipsLeft > 0) {
                p2Hits++;
                p1ShipsLeft--;
            }
        }
    }

    // Determine the winner based on ships left, then hits
    let winner = "Draw";
    if (p1ShipsLeft > p2ShipsLeft) {
        winner = p1;
        stats[p1].wins++;
    } else if (p2ShipsLeft > p1ShipsLeft) {
        winner = p2;
        stats[p2].wins++;
    } else if (p1Hits > p2Hits) {
        winner = p1;
        stats[p1].wins++;
    } else if (p2Hits > p1Hits) {
        winner = p2;
        stats[p2].wins++;
    } else {
        stats[p1].draws++;
        stats[p2].draws++;
        totalDraws++;
    }

    const result = {
        gameNumber: ++totalGames,
        player1: p1,
        player2: p2,
        winner,
        totalTurns,
        p1Hits,
        p2Hits,
        p1ShipsLeft,
        p2ShipsLeft,
        moveLog
    };

    gameHistory.unshift(result); // Store the game at the top of history
    render();
    renderLatestBoard(result.moveLog);
}

// Run multiple simulations at once
function simulateGames(n) {
    for (let i = 0; i < n; i++) simulateGame();
}

// Update everything on the page
function render() {
    document.getElementById("games-played").textContent = `Total Games Played: ${totalGames}`;
    document.getElementById("total-draws").textContent = `Total Draws: ${totalDraws}`;
    renderLeaderboard();
    renderGameHistory();
}

// Display stats per strategy
function renderLeaderboard() {
    const tbody = document.getElementById("leaderboard-body");
    tbody.innerHTML = "";
    strategies.forEach(strategy => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${strategy}</td>
            <td>${stats[strategy].wins}</td>
            <td>${stats[strategy].draws}</td>
        `;
        tbody.appendChild(row);
    });
}

// Show the most recent game outcomes
function renderGameHistory() {
    const container = document.getElementById("game-history");
    container.innerHTML = "";
    gameHistory.forEach(game => {
        const entry = document.createElement("div");
        entry.className = "game-entry";

        let winnerDisplay = game.winner === "Draw"
            ? `🤝 <strong>Draw</strong>`
            : `🏆 <strong>${game.winner} Wins</strong>`;

        entry.innerHTML = `
            <p><strong>Game #${game.gameNumber}:</strong> ${game.player1} vs ${game.player2} — ${winnerDisplay}</p>
            <button class="details-btn" onclick="toggleDetails(${game.gameNumber})">Details</button>
            <div class="game-details" id="details-${game.gameNumber}" style="display:none;">
                <p><strong>Player 1:</strong> ${game.player1}</p>
                <p><strong>Player 2:</strong> ${game.player2}</p>
                <p><strong>Total Turns:</strong> ${game.totalTurns}</p>
                <p><strong>${game.player1} Hits:</strong> ${game.p1Hits}</p>
                <p><strong>${game.player2} Hits:</strong> ${game.p2Hits}</p>
                <p><strong>${game.player1} Ships Left:</strong> ${game.p1ShipsLeft}</p>
                <p><strong>${game.player2} Ships Left:</strong> ${game.p2ShipsLeft}</p>
            </div>
        `;
        container.appendChild(entry);
    });
}

// Draw the visual board of last match
function renderLatestBoard(moveLog) {
    const board = Array.from({ length: 10 }, () => Array(10).fill("empty"));

    moveLog.forEach((entry, i) => {
        const role = i % 2 === 0 ? "p1-hit" : "p2-hit";
        const r = entry.row, c = entry.col;
        board[r][c] = entry.result === "HIT" ? role : "miss";
    });

    const container = document.getElementById("latest-board");
    container.innerHTML = "";
    board.forEach(row => {
        row.forEach(cell => {
            const div = document.createElement("div");
            div.classList.add("board-cell");
            switch (cell) {
                case "p1-hit": div.classList.add("cell-p1-hit"); div.innerText = "X"; break;
                case "p2-hit": div.classList.add("cell-p2-hit"); div.innerText = "X"; break;
                case "miss": div.classList.add("cell-miss"); div.innerText = "•"; break;
                default: div.classList.add("cell-empty"); break;
            }
            container.appendChild(div);
        });
    });
}

// Toggle visibility of game details
function toggleDetails(gameNumber) {
    const el = document.getElementById(`details-${gameNumber}`);
    el.style.display = el.style.display === "none" ? "block" : "none";
}

// Bind buttons to simulation actions
document.getElementById("simulate-one").addEventListener("click", () => simulateGame());
document.getElementById("simulate-ten").addEventListener("click", () => simulateGames(10));