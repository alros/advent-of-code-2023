package advent2023.d05;

import lombok.Getter;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import static java.lang.Long.parseLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;


public class Solution05 {


    public long step1(List<String> input) {
        List<Long> seeds = extractSeeds(input);
        Mappings mappings = extractMappings(input);

        Map<Long, Long> seedLocations = new HashMap<>();
        for(Long seed:seeds){
            seedLocations.put(seed, findLocation(seed, mappings));
        }

        return seedLocations.values().stream().sorted().findFirst().get();
    }

    public long step2(List<String> input) {
        List<Long> seeds = extractSeeds(input);
        Mappings mappings = extractMappings(input);

        Map<Long, Long> seedLocations = new HashMap<>();
        for(int i=0;i<seeds.size();i+=2){
            System.out.println("processing seed number: "+i);
            Long curSeed = seeds.get(i);
            Long range = seeds.get(i + 1);
            long start = System.currentTimeMillis();
            for(long seed = curSeed; seed< curSeed + range; seed++){
                if(seed % 100000 == 0){
                    System.out.println("- "+seed + " -> "+(curSeed+range)+ " in "+ (System.currentTimeMillis()- start));
                    start = System.currentTimeMillis();
                }
                seedLocations.put(seed, findLocation(seed, mappings));
            }
        }
        return seedLocations.values().stream().sorted().findFirst().get();
    }

    private Long findLocation(Long seed, Mappings mappings) {
        Long location = mappings.seedSoil.stream()
                .filter(m-> m.sourceRangeStart<=seed)
                .filter(m->seed<m.sourceRangeStart+m.rangeLength)
                .map(m->seed-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->seed);
        Long fertiliser = mappings.soilFertiliser.stream()
                .filter(m-> m.sourceRangeStart<=location)
                .filter(m->location<m.sourceRangeStart+m.rangeLength)
                .map(m->location-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->location);
        Long water = mappings.fertiliserWater.stream()
                .filter(m-> m.sourceRangeStart<=fertiliser)
                .filter(m->fertiliser<m.sourceRangeStart+m.rangeLength)
                .map(m->fertiliser-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->fertiliser);
        Long light = mappings.waterLight.stream()
                .filter(m-> m.sourceRangeStart<=water)
                .filter(m->water<m.sourceRangeStart+m.rangeLength)
                .map(m->water-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->water);
        Long temperature = mappings.lightTemperature.stream()
                .filter(m-> m.sourceRangeStart<=light)
                .filter(m->light<m.sourceRangeStart+m.rangeLength)
                .map(m->light-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->light);
        Long humidity = mappings.temperatureHumidity.stream()
                .filter(m-> m.sourceRangeStart<=temperature)
                .filter(m->temperature<m.sourceRangeStart+m.rangeLength)
                .map(m->temperature-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->temperature);
        return mappings.humidityLocation.stream()
                .filter(m-> m.sourceRangeStart<=humidity)
                .filter(m->humidity<m.sourceRangeStart+m.rangeLength)
                .map(m->humidity-m.sourceRangeStart+m.destinationRangeStart)
                .sorted((a,b)->Long.compare(b,a))
                .findFirst()
                .orElseGet(()->humidity);
    }

    private List<Long> extractSeeds(List<String> input) {
        return Arrays.asList(input.get(0).split(" "))
                .stream()
                .filter(s -> s.matches("\\d+"))
                .map(Long::parseLong)
                .toList();
    }

    private Mappings extractMappings(List<String> input) {
        int index = 3;

        List<Mapping> seedSoil = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            seedSoil.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> soilFertiliser = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            soilFertiliser.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> fertiliserWater = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            fertiliserWater.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> waterLight = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            waterLight.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> lightTemperature = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            lightTemperature.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> temperatureHumidity = new ArrayList<>();
        for (;input.get(index).matches("\\d.*");index++) {
            temperatureHumidity.add(extractMap(input.get(index)));
        }
        index+=2;

        List<Mapping> humidityLocation = new ArrayList<>();
        for (;index < input.size();index++) {
            humidityLocation.add(extractMap(input.get(index)));
        }

        return new Mappings(
                seedSoil,
                soilFertiliser,
                fertiliserWater,
                waterLight,
                lightTemperature,
                temperatureHumidity,
                humidityLocation
        );
    }

    private Mapping extractMap(String line) {
        String[] values = line.split(" ");
        return new Mapping(parseLong(values[0]),parseLong(values[1]),parseLong(values[2]));
    }

    record Mapping(long destinationRangeStart,long sourceRangeStart,long rangeLength) {}

    record Mappings(
            List<Mapping> seedSoil,
            List<Mapping> soilFertiliser,
            List<Mapping> fertiliserWater,
            List<Mapping> waterLight,
            List<Mapping> lightTemperature,
            List<Mapping> temperatureHumidity,
            List<Mapping> humidityLocation
    ){}
}