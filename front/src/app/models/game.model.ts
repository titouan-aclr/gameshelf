import { GameCategory } from "./game-category.type";

export class Game {

    constructor(
        public id: number,
        public name: string,
        public description: string,
        public imageUrl: string,
        public yearPublished: number,
        public minPlayers: number,
        public maxPlayers: number,
        public playingTime: number,
        public gameCategory: GameCategory[],
    ) { }

}
