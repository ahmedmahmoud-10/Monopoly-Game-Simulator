# 2025W CS2910 Project Report
**Group Members**
1. Ahmed Khalil (359784)
2. Ahmed Ali (352028)
# Summary of Game
Battleship is a 2-player strategy game where each player places a fleet of ships on a grid and takes turns guessing the coordinates of the opponent’s ships. The main goal of the game is to sink all of the opponent’s ships by guessing their positions correctly. There are different shops, which all vary in size and how they occupy consecutive squares (horizontal or vertical). The key objects in the game include the Board, which tracks ship placements and hits/misses; Ships, which have size and health; and Players, who use strategies to select attack positions. The rules can also be read on https://www.hasbro.com/common/instruct/Battleship.PDF

# Experiment Report
## Player Strategies

**1. Aggressive:** The aggressive strategy is dependent on the relentless offensive approaches by targeting the top-left quadrant of the board. The strategy operates on an assumption that the opponent will place the ships in predictable patterns near the origin and exploits this with repeated attacks towards that area. This kind of player doesn’t adapt any flexibility, the main goal is for it to secure their hits early and reduce enemy ships. This strategy is very effective when opponents aren’t prepared, however, it struggles against a random or decentralized approach with ship placements. The strategy simulates an AI that prioritizes speed over accuracy, which makes it best fit for quick, high-risk battles with a large potential for quick wins or losses.

**2. Greedy:** The greedy strategy follows a different approach, it mainly exploits quick gains by targeting adjacent cells after each attempted hit. Once a potential target has been identified, it queues up the nearby cells, with a main goal of sinking that specific ship quickly through hits to nearby spots. The strategy isn’t ideal for long-term planning, however, it’s very effective in identifying and eliminating individual ships, one at a time. Greedy players are highly dependent on fast lops to adjust the attacks dynamically throughout the game. The approach follows a “chasing” mindset, with the player mainly valuing short-term advantages over covering the whole board. It’s most effective if the first hit is made early into the game.

**3. Random:** The random strategy is based on luck and unpredictability. Each move is independent than the previous one. Attacks are launched with completely random coordinates on the grid, with no memory, pattern recognition or adjustment after every hit/miss. At first, this may seem inefficient, however, if coming up again players expecting pattern-based strategies, the random strategy is surprisingly effective. The strategy ensure unbiased coverage over time, however, it’s lacks precision or targeted behaviours that we see in smarter AI strategies. Overall, this strategy is very simple to implement and is a perfect pick for adding a diverse and unpredictable matchup.

**4. Smart:**  The Smart strategy can be considered as an improved version of the Random one as it includes the concept of memory and awareness. The strategies maintains a set of coordinates that have already been attacked and ensure there’s no repeat moves. Although it’s still random in selection, it avoids redundancy, maximizes board coverage and ensure efficiency. The strategy is built on a logical AI that learns from it’s own actions and avoids any waste. Through the combination of unpredictable and intelligent decision making, the strategy has the most consistent performance across most games. Smart players are a perfect fit against a strategy prone to wasting moves or following repeat/predictable patterns. Overall, although it’s not an aggressive strategy, it creates a balance, which makes it an extremely steady approach.

**5. Strategic:** The strategic approach is quite similar to the Smart approach. A strategic player follows a patterned, chessboard-like movement. One main approach is alternating the columns or rows to help maximize the ship detection, which is based on the assumption that ships will occupy more than 1 cell. For example, it might fire at ever 2nd cell, which creates a checkerboard-like attack patter. Through this logic, there’s an increased chance of hitting ships without needing to cover every single square. Once hits are, they’re nit chased like Greedy, they continue to scan like the Smart approach. Overall, Strategic is a very highly calculated and methodical approach, it is beneficial in longer games and has a lot less wasted shots.

## Procedure

For our procedure, we designed and executed a Battleship simulation framework that tests 5 different AI strategies—Aggressive, Greedy, Smart, Strategic, and Random. All of the strategies we’re put into a test against one another. We had 2 testing options, our first one was a simple Simulation (SimulateExperiment.java), which simply ran 50 games and generated results like total wins per strategy and draws. The 2nd testing option was one with more detail (DetailedSimulationRunner.java), it simulated 10 games and outputted the results, and an in-depth analysis of the game like the player moves, hits, misses, etc. This 2nd testing option was the chosen method of simulation as it provided us with a lot more detail, which we believe will be more helpful and supportive for our data. Each simulation initiates two AI players with distinct strategies and runs a complete game of Battleship from ship placement to the final turn.

Moreover, we set our number of trials to about 500 attempts. These are going to be 500 detailed simulations to help give us as much details as possible. There will be 100 simulations of each strategy to ensure a fair and accurate result manipulation. As mentioned, the detailed simulation will provide us with deeper insight into individual matches by recording turn-by-turn move data, including hit/miss results, ships, etc. This data will be extremely beneficial in our detailed analysis of player efficiency and their attacking behaviour.






Our outcome results are all automatically exported into 4 different CSV files:

**game_results.csv:** Stores the outcome of each simulated game, showing which two strategies played and which one won (or if it was a draw).

**strategy_wins.csv:** Tracks the total number of wins and draws per strategy across all simulations.

**game_stats.csv:** Provides detailed statistics for each game, including strategies used, turns taken, hits, and remaining ships for both players.

**detailed_game_log.csv:** Logs every move of every game, including coordinates and whether each shot was a hit or miss, offering a full play-by-play history.

Our main objective is to use all the data we’ve managed to export from the findings and create visuals that will help us analyse the findings and create multiple angles of interpretation. As part of our analysis, we focused on 3 different combinations. 

Three major comparisons were emphasized:

**Aggressive vs Smart (brute force vs calculated play)**

**Greedy vs Strategic (localized targeting vs board-wide probability scanning)**

**Random 5-strategy free-for-all.**

We picked 4 of the strongest strategies in our opinion and matched them against their strongest competitor. We also had a final combination, which was a random mixup of all 5 of the combinations randomly. This approach gave us not only win/loss data, but also a functional understanding of how strategy logic affects performance across varied conditions.

## Results
#### **Table #1: Smart vs Aggressive (50 Game Simulation — Results Summary)**

| Strategy    | Wins | Losses | Draws |
|-------------|------|--------|-------|
| Smart       | 10   | 32     | 8     |
| Aggressive  | 32   | 10     | 8     |

The table above compares the performance of the Smart and Aggressive strategies in a 50-game simulation. The Aggressive strategy won 32 games, while the Smart strategy only won 10. Both strategies had 8 draws, indicating that they were evenly matched in some cases.
 #### **Figure 1.0: Smart vs Aggressive Win % Bar Graph**
![](graph1.png) 
The figure above shows the win percentage of each strategy in the 50-game simulation. Aggressive clearly outperformed Smart, winning 64% of the games compared to Smart's 20%. 

#### Table #2: Smart vs Aggressive More Detailed Simulation (50 Game Simulation)


| Strategy    | Wins | Win %  | Avg Hits | Avg Ships Left |
|-------------|------|--------|----------|----------------|
| Smart       | 10   | 20.0%  | 15.14    | 0.06           |
| Aggressive  | 32   | 64.0%  | 15.82    | 0.46           |
| Draw        | 8    | 16.0%  | —        | —              |

This 50-game simulation shows Aggressive outperforming Smart in wins and survivability. Despite Smart’s tactical approach, it struggles against Aggressive’s dominance. The draw rate suggests occasional balance, but Aggressive clearly holds the advantage overall.

#### Figure 2.0: Smart vs Aggressive Scatter Plot 
![](scatter1.png)
Aggressive strategies dominate with higher hits and more ships left, clustering in the upper-right of the scatter plot. Smart strategies survive narrowly, often winning with minimal ships. The plot highlights Aggressive’s consistent, overwhelming playstyle versus Smart’s riskier, defensive approach. Efficiency and survivability clearly favor Aggressive in this matchup.

#### Table #3: Greedy vs Strategic (50 Game Simulation — Results Summary)
Below is a summary of win/loss/draw outcomes from the first 50 recorded games between **Greedy** and **Strategic** strategies.

| Strategy    | Wins | Losses | Draws |
|-------------|------|--------|-------|
| Greedy      | 19   | 28     | 3     |
| Strategic   | 28   | 19     | 3     |

*Strategic leads with a clear edge in win count over Greedy. Draws occurred infrequently, showing a more decisive matchup.*

#### Figure 3.0: Greedy vs Strategic Win % Bar Graph
![](graph2.png)
The figure above shows the win percentage of each strategy in the 50-game simulation. Strategic outperformed Greedy, winning 56% of the games compared to Greedy’s 38%. This highlights Strategic’s consistency and edge in head-to-head matchups.


#### Table #4: Greedy vs Strategic More Detailed Simulaton (50 Game Simulation)
This table dives deeper into the average performance metrics across the 50 games.

| Strategy    | Wins | Win %  | Avg Hits | Avg Ships Left |
|-------------|------|--------|----------|----------------|
| Greedy      | 17   | 34.0%  | 14.70    | 0.50           |
| Strategic   | 28   | 56.0%  | 15.85    | 0.80           |
| Draw        | 5    | 10.0%  | —        | —              |

*Strategic not only wins more but also retains more ships on average, suggesting greater tactical durability compared to the Greedy approach.*

#### Figure 4.0: Greedy vs Strategic Scatter Plot
![](scatter2.png)
This scatter plot visualizes the relationship between hits landed and ships left for Greedy and Strategic strategies across 50 simulated games.

#### Table 5: Random Selection Of Strategy Match-ups (500 Fully Random Game Simulations)

| Strategy    | Wins | Draws | Losses | Avg Hits | Avg Ships Left |
|-------------|------|-------|--------|----------|----------------|
| Aggressive  | 45   | 10    | 45     | 15.64    | 0.43           |
| Smart       | 38   | 14    | 48     | 15.31    | 0.30           |
| Greedy      | 30   | 10    | 60     | 15.02    | 0.24           |
| Strategic   | 46   | 8     | 46     | 15.77    | 0.44           |
| Random      | 37   | 13    | 50     | 15.22    | 0.29           |

This table summarizes how each strategy performed in its first 100 simulated games. Strategic and Aggressive lead in wins and survivability. Smart and Random show more balanced outcomes, while Greedy lags in both wins and average performance stats.

#### Figure 5.0: Random Selection Of Strategy Match-ups Win % Bar Graph
![](graph3.png)
## Analysis

Before we began the simulations, we had set a few predictions out. We expected the Aggressive and Strategic strategies to outperform the other strategies. We thought so due to the smart and direct approaches of both strategies. The aggressive strategy thrives on the pressure and forceful attacks, whilst Strategic plays a longer game with every decision being calculated. Out of the 5 strategies, they had the strongest balance of offence and defence.

Our first head-to-head comparison was between the Smart and Aggressive strategies. As we can see in table 1 and figure 1.0, aggressive was the dominant strategy in the match-up. With 64% of the wins going to aggressive and 20% going to smart. However, it’s was slightly unexpected to see that 16% of the games ended in draws. Given the approaches of both strategies, we expected a more one-sided outcome, in favour of the aggressive strategy. However, the high draw rate showed that smart could hold on, but not necessarily finish strong.

To understand this better, we explored the performance in more depth. Despite Aggressive having significantly more wins, the average number of hits was only slightly higher (15.82 for Aggressive vs. 15.14 for Smart). This minor difference suggests that both were similarly effective at landing shots. The real difference, however, came from average ships left: Aggressive preserved an average of 0.46 ships, while Smart barely held onto 0.06. This means Aggressive not only won more games but finished with a lot more ships.

Looking at Figure 1.1, the difference becomes very clear. Aggressive strategy points were generally higher on the Y-axis, meaning those games ended with more surviving ships. Smart’s data points clustered near the bottom, which shows its frequent ship losses. This confirmed that although Smart could hold its own offensively, it struggled defensively.

In our second comparison, we looked at Greedy vs. Strategic. As seen in Table 3, Strategic came out on top with 28 wins compared to Greedy’s 17. This match-up was closer than Smart vs. Aggressive, but the winner was still clear. The Greedy strategy, which focuses on relentlessly attacking a ship once found, surprised us with how well it performed. Despite lacking adaptability or long-term planning, it was able to win a decent number of games.

However, when we dug deeper, the difference was clear. As seen in Table 4 and Figure 4.0, Strategic averaged 15.85 hits per game, compared to Greedy’s 14.70. While that difference seems small on paper, it’s very meaningful in tighter games where each hit can decide the outcome. Additionally, Strategic averaged 0.80 ships left, which is significantly more than Greedy’s 0.50. This means it not only won more often but did so with less damage taken. In Figure 4.0, you can clearly see Strategic’s wins clustering higher on both hits and survivability, while Greedy shows more scattered, lower-performing outcomes. Thus, displaying how much more effective the Strategic strategy is compared to the Greedy strategy .

Finally, our last simulation to tie everything together, we ran a 500-game simulation, giving each strategy 100 randomized match-ups. This allowed us to see how each would perform in an unbiased setting—no opponent was chosen deliberately; everything was randomized.

Graph 5 and Table 5 highlighted a few key takeaways. First off, as expected, Strategic and Aggressive had the highest win counts, 46 and 45 wins. Strategic edged out the others in average hits (15.77) and average ships left (0.44), making it the most well-rounded and consistent performer. Aggressive, with a 45/45 win-loss split, further proved its high-risk, high-reward nature. It scores big, but also loses big.

Smart showed decent performance with 38 wins and 14 draws. However, it had the second-lowest average ships left (0.30), which again shows its poor survivability. It’s capable of long games and occasional wins but fails to dominate.

Random and Greedy lagged behind. Random managed 37 wins and held onto an average of 0.29 ships, while Greedy only managed 30 wins and the lowest survivability (0.24). Greedy was the least consistent. Even when it scored hits, it struggled to survive the counterattacks.

Overall, Strategic stood out as the most reliable and effective strategy as it was winning frequently, hitting accurately, and defending well. Aggressive followed closely, offering powerful plays but with volatility. Smart had potential but lacked performing in the long run against stronger opponents. Random showed some strength through unpredictability, and Greedy consistently came up short in both attack and defence.

This deeper analysis proved our early predictions to be correct, Strategic and Aggressive are the strongest, but Strategic’s balance between offence and defence gives it the upper hand in the long run.

# Reflection

The most commonly used tool in our project was ChatGPT (Chat). There were many different ways that we were able to use chat. The main purpose of our use of Chat was to have him guide us and explain certain concepts to us that were a bit too complex or confusing. There were numerous cases where both me and Ahmed weren’t aware of what next steps we should take, what approaches we should move towards, etc. This is where Chat was helpful, as it was able to break down the tasks for us and explain how we can solve our problems. In most cases, chat was extremely helpful, especially with the fact that we could ask him numerous questions without a limit. Furthermore, we used Chat to help create visuals for our data. Part of our project was having our data logged in CSV files, which we would provide Chat and have it create us visuals, and even explain data we didn't fully understand. We would use these visuals and analyse them to help present our data. Although it may seem like Chat was great, there were many flaws. As mentioned, we would use Chat to break down certain concepts. However, chat sometimes would break down concepts with too much complexity, which would lead to even more confusion. This was very frustrating for the both of us, as we’d feel stuck and lost. Additionally, we also had problems with the Chat’s data processing. There’d be numerous cases where we provided Chat with the data and it would incorrectly process the data. Whether it was reading columns wrong or messing up the counts of certain wins/losses, the whole process of using Chat became a problem for us. Luckily, we were able to manage and figure things out slowly, which helped us create this final product.

Chat wasn’t the only AI tools that we used. We also decided to use GitHub’s Co-Pilot, which we found much easier to use that Chat. Our main use of Co-Pilot was to help us generate getters and setters, and test cases, which we’d of course use to ensure our code works. Co-Pilot was directly connected to IntelliJ, which made everything so much easier. The AI had full access to all our local files, which made our lives much easier. With chat, we would’ve had to send the code back and fourth, however, with co-pilot, the AI had quicker access to the code. We believe that Chat was a great tool to use, however, co-pilot was much easier and caused us less issues.

One thing we both agreed on if we would have to repeat this project is simulate the test cases differently. We both found that sometimes the test cases would be confusing and inaccurate and had to make many adjustments. For example, for the max amount of tunrs, we originally set it to 35, which is the average amount of moves in a game. However, that was too little, and resulted in too many draws. When we tried doubling it to 70, we still found the same issue. We only managed to come up with a correct figure after multiple tests with different numbers, which took a lot more time than it should've. We could've maybe used AI to help guide us in that sense, but we didn't think Chat or Co-Pilot would've been of much help. 

Overall, we firmly believe that our use of AI was a complete success. The way we used AI was done very cautiously to avoid violating any academic rules, whilst also benefiting from the information provided. The code and information written in our project was done by us with Chat and Co-pilot guiding us in situations where we felt lost. We also believe the way we used AI helped us understand our code more. As the AI broke down the concepts and generated our test cases, we gained more understanding of how the code works. Overall, we believe this helped us a ton while working on the project as we gained more knowledge and developed a strong coding project. Thus, meeting the expected outcomes of the project.

# Bonus Consideration:

For our bonus, we created a website that can be used as a visual tool to simulating the game. It also includes detailed statistics for each strategy, wins, draws, and a visual board of the most recent simulated game. This interactive website allows users to simulate one or multiple battles between five predefined strategies: Random, Greedy, Smart, Strategic, and Aggressive.

The simulation logic is fully written in JavaScript and features a randomized board with turns alternating between two AI players. The players attack the opponent ships based on probability and ensured each moved is logged with visual feedback. We also included stats like total hits, ships remaining and the final game outcome as an option for the user to view. Furthermore, we have a leaderboard, which is dynamically updated accordingly following the result of a simulated game.

We included three core features:

- “Simulate One Game”: runs a single match and displays the results.

- “Simulate 10 Games”: runs 10 matches and displays results.

- Real-time Visual Board: displays each move for both players.

This tool goes beyond project requirements by offering unique experience for users. It also allows them to understand how different AI strategies perform in various matchups. We ensured compatibility with our CSS/HTML structure and made sure the website was clear, responsive, and visually appealing. All 3 files were pushed to GitHub alongside our main Java codebase. Feel free to check it out and attempt to try the website. We've also attached images below of the website for your reference. 

![](web1.png)

![](web2.png)

![](web3.png)


