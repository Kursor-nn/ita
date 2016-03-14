package com.kozachuk.ita.Commands;

import com.kozachuk.ita.Persistance.Model.Category;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by alexanderkozachuk on 14.03.16.
 */
public class GetCategoriesCommand extends Command {

    public GetCategoriesCommand(){}

    public GetCategoriesCommand(Session session){
        super(session);
    }

    @Override
    public List run() {
        List<Category> categoryList = session.createCriteria(Category.class).addOrder(Order.asc("name")).list();
        return categoryList;
    }
}
