function div(val, className) {
    return `<div${className ? ' class="' + className + '"' : ''}>${val}</div>`
}
//insert into film_category(film_id,category_id) 
//select film_id, (category_id+1)%(select max(category_id) from category)+1 from film_category;


//insert into film_category(film_id,category_id) 
//select film_id, (category_id+2)%(select max(category_id) from category)+1 from film_category where film_id%2=0;

export function table(data, title) {
    console.log(title, data.length);
    if (!data) return '';
    return div(`<div id=${title} class='caption'>${title} ${data.length} actors</div>` + div(['Actor', 'Film', 'Category'].map(v => div(v)).join(''), 'row header') +
        data.
            slice(0, 10)
            .flatMap((a, ai) =>
                a.films.flatMap((f, fi) =>
                    f.categories.map((c, ci) => div(div(a.lastName + " " + a.firstName, 'actor f' + fi + ' c' + ci)
                        + div(f.title, 'film f' + fi + ' c' + ci) + div(c.name, 'category f' + fi + ' c' + ci), 'row'))
                )
            ).join(''), 'table ' + title);
}
