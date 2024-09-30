import { Injectable } from "@angular/core";
import { Game } from "../models/game.model";

@Injectable({
    providedIn: 'root',
})
export class GamesService {
    private games: Game[] = [
        new Game(
            394889,
            'Cabanga!',
            'In Cabanga!, players try to get rid of their hand of cards as quickly as possible &amp;mdash; but ideally without picking up penalties along the way.&amp;#10;&amp;#10;After the row cards and starting cards in all four colors have been placed in the middle of the table and players each have a hand of eight cards, the round begins. Players then take turns placing one card next to the matching row card in the middle, ideally with as small a difference as possible because the larger the number gap, the greater the chance that the other players will call out &amp;quot;Cabanga!&amp;quot; and throw cards with the values between the two number cards to the active player. These thrown-in cards are placed on the discard pile, then the active player must draw the same number of cards from the penalty pile.&amp;#10;&amp;#10;When a player has no more cards in hand, the round ends and all players count the points on their cards. As soon as a player has collected 18 points, the game ends and the player with the fewest points wins!&amp;#10;&amp;#10;',
            'https://cf.geekdo-images.com/nbGlxygT3CGhM7L-9_1gkQ__original/img/gOKpNlUbsbc8AXDqKLcSMa5xFTA=/0x0/filters:format(jpeg)/pic7655903.jpg',
            2023,
            3,
            6,
            20,
            ['Card Game'],
        ),
        new Game(
            13,
            'Catan',
            'In CATAN (formerly The Settlers of Catan), players try to be the dominant force on the island of Catan by building settlements, cities, and roads. On each turn dice are rolled to determine what resources the island produces. Players build by spending resources (sheep, wheat, wood, brick and ore) that are depicted by these resource cards; each land type, with the exception of the unproductive desert, produces a specific resource: hills produce brick, forests produce wood, mountains produce ore, fields produce wheat, and pastures produce sheep.&amp;#10;&amp;#10;Set-up includes randomly placing large hexagonal tiles (each showing a resource or the desert) in a honeycomb shape and surrounding them with water tiles, some of which contain ports of exchange. Number disks, which will correspond to die rolls (two 6-sided dice are used), are placed on each resource tile. Each player is given two settlements (think: houses) and roads (sticks) which are, in turn, placed on intersections and borders of the resource tiles. Players collect a hand of resource cards based on which hex tiles their last-placed house is adjacent to. A robber pawn is placed on the desert tile.&amp;#10;&amp;#10;A turn consists of possibly playing a development card, rolling the dice, everyone (perhaps) collecting resource cards based on the roll and position of houses (or upgraded cities&amp;mdash;think: hotels) unless a 7 is rolled, turning in resource cards (if possible and desired) for improvements, trading cards at a port, and trading resource cards with other players. If a 7 is rolled, the active player moves the robber to a new hex tile and steals resource cards from other players who have built structures adjacent to that tile.&amp;#10;&amp;#10;Points are accumulated by building settlements and cities, having the longest road and the largest army (from some of the development cards), and gathering certain development cards that simply award victory points. When a player has gathered 10 points (some of which may be held in secret), he announces his total and claims the win.&amp;#10;&amp;#10;CATAN has won multiple awards and is one of the most popular games in recent history due to its amazing ability to appeal to experienced gamers as well as those new to the hobby.&amp;#10;&amp;#10;Die Siedler von Catan was originally published by KOSMOS and has gone through multiple editions. It was licensed by Mayfair and has undergone four editions as The Settlers of Catan. In 2015, it was formally renamed CATAN to better represent itself as the core and base game of the CATAN series. It has been re-published in two travel editions, portable edition and compact edition, as a special gallery edition (replaced in 2009 with a family edition), as an anniversary wooden edition, as a deluxe 3D collector&amp;#039;s edition, in the basic Simply Catan, as a beginner version, and with an entirely new theme in Japan and Asia as Settlers of Catan: Rockman Edition. Numerous spin-offs and expansions have also been made for the game.&amp;#10;&amp;#10;',
            'https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg',
            1995,
            3,
            4,
            120,
            ['Economic', 'Negociation'],
        ),
        new Game(
            117663,
            'Mille Sabords!',
            "Piraten Kapern (&amp;quot;Pirate Capers&amp;quot;) is the German version of the 2011 game Otsarot o Tsarot, published by Shafir Games in Hebrew and English. It's part of a line of combined dice-and-card games that publisher AMIGO Spiel introduced in 2012.&amp;#10;&amp;#10;As you might expect with a game titled Piraten Kapern, players must set off in search of treasure, pushing themselves to find as much as possible without losing their heads.&amp;#10;&amp;#10;At the start of a turn, the active player rolls the eight special dice. He must set aside any skulls rolled, and his turn ends immediately with no score if he rolls a third skull. The player is free to set aside any number of other dice, rerolling the rest. He may continue to do this until the skulls get him or he stops. He then scores for dice combinations, with a three-of-a-kind earning 100 points, a four-of-a-kind 200 points, and so on all the way up to an eight-of-a-kind earning 4,000 points. Each gold or diamond showing is worth 100 points on its own. A player earns a 500 point bonus if all eight dice score.&amp;#10;If a player is not able to set aside any valid dice, all points are lost.&amp;#10;&amp;#10;If a player rolls four or more skulls on his first roll, he heads to Skull Island for the turn, setting aside those skulls and continuing to roll as long as he sets aside at least one skull each time. Once he stops, all other players lose 100 points for each skull showing.&amp;#10;&amp;#10;Before a player starts to roll for his turn, however, he draws a card from the pirate deck, which affects what's possible on that turn: He might score double for the treasure he collects, or need to roll sabers to fight off other pirates, or receive a free diamond or gold coin, or be able to score treasure even if he collect three skulls.&amp;#10;&amp;#10;Once a player reaches 6,000 points, all other players take one final turn, then the player with the most points wins.&amp;#10;&amp;#10;",
            'https://cf.geekdo-images.com/ASvNr8Z7n4uuXiIeKTCpjA__original/img/wFq8_y9ycpinYVvBxwxraxix91c=/0x0/filters:format(jpeg)/pic5755894.jpg',
            2012,
            2,
            5,
            30,
            ['Card Game', 'Dice'],
        ),
        new Game(
            1406,
            'Monopoly',
            "Theme&amp;#10;Players take the part of land owners, attempting to buy and then develop their land. Income is gained by other players visiting their properties and money is spent when they visit properties belonging to other players. When times get tough, players may have to mortgage their properties to raise cash for fines, taxes and other misfortunes.&amp;#10;&amp;#10;Gameplay&amp;#10;On his turn, a player rolls two dice and moves that number of spaces around the board. If the player lands on an as-yet-unowned property, he has the opportunity to buy it and add it to his portfolio or allow the bank to auction it to the highest bidder. If a player owns all the spaces within a color group, he may then build houses and hotels on these spaces, generating even more income from opponents who land there. If he lands on a property owned by another player, he must pay that player rent according to the value of the land and any buildings on it. There are other places on the board which can not be bought, but instead require the player to draw a card and perform the action on the card, pay taxes, collect income, or even go to jail.&amp;#10;&amp;#10;Goal&amp;#10;The goal of the game is to be the last player remaining with any money.&amp;#10;&amp;#10;Cultural impact on rules&amp;#10;Monopoly is unusual in that the game has official, printed rules, but most players learn how to play from others, never actually learning the correct way to play. This has led to the canonization of a number of house rules that make the game more palatable to children (and sore losers) but harm the gameplay by preventing players from going bankrupt or slowing down the rate of property acquisition. One common house rule has players put any money paid to the bank in the center of the board, which jackpot a player may earn by landing on Free Parking. This prevents the game from removing money from play, and since players collect $200 each time they pass Go, this results in ever-increasing bankrolls and players surviving rents that should have bankrupted them. Another house rule allows players to take &amp;quot;loans&amp;quot; from the bank instead of going bankrupt, which means the game will never end. Some house rules arise out of ignorance rather than attempts to improve the game. For instance, many players don't know that properties landed on but left unbought go up for auction, and even some that know to auction don't know that the bidding starts at $1, meaning a player may pay well below the listed price for an auctioned property.&amp;#10;&amp;#10;",
            'https://cf.geekdo-images.com/9nGoBZ0MRbi6rdH47sj2Qg__original/img/bA8irydTCNlE38QSzM9EhcUIuNU=/0x0/filters:format(jpeg)/pic5786795.jpg',
            1935,
            2,
            8,
            180,
            ['Economic']
        ),
        new Game(
            39856,
            'Dixit',
            "",
            'https://cf.geekdo-images.com/J0PlHArkZDJ57H-brXW2Fw__original/img/jt3kFCHJ3HJ2079dMLwipFZqdQg=/0x0/filters:format(jpeg)/pic6738336.jpg',
            2008,
            3,
            6,
            30,
            ['Card Game', 'Party Game']
        )
    ];

    getGames(): Game[] {
        return [...this.games];
    }

    getGameById(gameId: number): Game {
        const foundGame = this.games.find((game) => game.id === gameId);
        if (!foundGame) {
            throw new Error(`Game with id ${gameId} not found`);
        }
        return foundGame;
    }
}