import { table } from './table.js';
const BASE_URL = '/api/';

const PATHS = ['jdbc', 'findAll', 'findAllSorted', 'jpa', 'query', 'queryDistinct'];
const links = '<div class="links">' + PATHS.map(p => `<a href="#${p}">${p}</a>`).join('') + '</div>';
//  
Promise.all(
    PATHS.map(path => {
        console.time(path);
        return fetch(BASE_URL + path).then(r => r.json()).then(o => {
            console.timeLog(path);
            const html = table(o, path);
            console.timeEnd(path);
            return html;
        });
    }))
    .then(ar => {
        console.time("render");
        rootDiv.innerHTML = links + ar.join('')
        console.timeEnd("render");
    });

