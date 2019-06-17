package by.taining.task4.view;

import by.taining.task4.entity.Candy;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileWriter implements Writer {

    @Override
    public void write(String str, List list ) {

        ArrayList<String> lines = new ArrayList<>();


        lines.add("ID      Name                           Type                      TypeOfChocolate    Carbohyd  Enrgy        Fats       Fructose   Proteins   Sugar      Vanillin   Water");
        lines.add("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Object o : list) {
// System.out.println(((Candy)o).getId() + "    " + ((Candy)o).getName() + "    " + ((Candy)o).getProduction()  + "    " + ((Candy)o).getType() + "    " + ((Candy)o).getTypeOfChocolate() + "    " + ((Candy)o).getCarbohydrates() + "    " + ((Candy)o).getEnrgy() + "    " + ((Candy)o).getFats() + "    " + ((Candy)o).getFructose() + "    " + ((Candy)o).getProteins() + "    " + ((Candy)o).getSugar() + "    " + ((Candy)o).getVanillin() + "    " + ((Candy)o).getWater());
            String output = String.format("%-7s %-30s %-25s %-10s %10d %10d %10d %10d %10d %10d %10d %10d",((Candy)o).getId(),((Candy)o).getName(),((Candy)o).getType(),((Candy)o).getTypeOfChocolate(),((Candy)o).getCarbohydrates(),((Candy)o).getEnrgy(),((Candy)o).getFats(),((Candy)o).getFructose(),((Candy)o).getProteins(),((Candy)o).getSugar(),((Candy)o).getVanillin(),((Candy)o).getWater());
            lines.add(output);
        }










        Path path = Paths.get(str);

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (Exception e) {

        }
    }
}
