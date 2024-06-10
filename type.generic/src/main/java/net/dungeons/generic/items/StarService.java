package net.dungeons.generic.items;

import net.dungeons.generic.util.Stringify;

public class StarService {
    public static String getStarString(int stars)
    {
        if (stars > 5)
        {
            String star = Stringify.formatString("&c");
            int cut = stars - 5;

            for (int i = 0; i < 5; i++)
            {
                if (i == cut)
                    star += Stringify.formatString("&6");
                star += "✪";
            }

            return star;
        }
        else
        {
            String star = Stringify.formatString("&6");
            for (int i = 0; i < stars; i++)
            {
                star += "✪";
            }

            return star;
        }
    }
}