import { Routes } from '@angular/router';
import { GameDetailsComponent } from './components/game-details/game-details.component';
import { GameListComponent } from './components/game-list/game-list.component';

export const routes: Routes = [
    { path: "mes-jeux/:id", component: GameDetailsComponent },
    { path: "mes-jeux", component: GameListComponent }
];
