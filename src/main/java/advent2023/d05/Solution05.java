package advent2023.d05;

import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Long.parseLong;


public class Solution05 {


    public long step1(List<String> input) {
        ToSkip toSkip = new ToSkip();
        List<Long> seeds = extractSeeds(input);
        Mappings mappings = extractMappings(input);

        Map<Long, Long> seedLocations = new HashMap<>();
        for (Long seed : seeds) {
            Optional<Long> location = findLocation(seed, mappings, toSkip);
            if (location.isPresent()) {
                seedLocations.put(seed, location.get());
            }
        }

        return seedLocations.values().stream().sorted().findFirst().get();
    }

    public long step2(List<String> input) {
        List<Long> seeds = extractSeeds(input);
        Mappings mappings = extractMappings(input);

        Map<Long, Long> seedLocations = new ConcurrentHashMap<>();
        Set<Thread> threads = createThreads(seeds, seedLocations, mappings);
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return seedLocations.values().stream().sorted().findFirst().get();
    }

    private Set<Thread> createThreads(List<Long> seeds, Map<Long, Long> seedLocations, Mappings mappings) {
        Set<Thread> threads = new HashSet<>();
        ToSkip toSkip = new ToSkip();

        for (int i = 0; i < seeds.size(); i += 2) {
            final int index = i;
            Thread t = new Thread(() -> {
                Long curSeed = seeds.get(index);
                Long range = seeds.get(index + 1);
                long start = System.currentTimeMillis();
                long operations = 0;
                for (long seed = curSeed; seed < curSeed + range; seed++) {
                    operations++;
                    if (System.currentTimeMillis() - start > 5 * 60 * 1000) {
                        System.out.println("thread " + index + " speed " + operations + "/5 mins");
                        start = System.currentTimeMillis();
                        operations = 0;
                    }
                    if (!seedLocations.containsKey(seed)) {
                        Optional<Long> location = findLocation(seed, mappings, toSkip);
                        if (location.isPresent()) {
                            seedLocations.put(seed, location.get());
                        }
                    }
                }
            });
            threads.add(t);
        }
        return threads;
    }

    private Optional<Long> processMapping(List<Mapping> mappings, Long key){
        List<Mapping> mappingList = mappings.stream()
                .filter(m -> m.getSourceRangeStart() <= key)
                .filter(m -> key < m.getSourceRangeStart() + m.getRangeLength())
                .toList();
        return Optional.ofNullable(mappingList.stream().map(m -> key - m.getSourceRangeStart() + m.getDestinationRangeStart())
                .sorted((a, b) -> Long.compare(b, a))
                .findFirst()
                .orElseGet(() -> key));
    }

    private Optional<Long> findLocation(Long seed, Mappings mappings, ToSkip toSkip) {
        Optional<Long> soil = processMapping(mappings.seedSoil, seed);
        if(soil.isEmpty() || toSkip.getSoil().contains(soil.get())){
            return Optional.empty();
        }else{
            toSkip.getSoil().add(soil.get());
        }

        Optional<Long> fertiliser = processMapping(mappings.soilFertiliser, soil.get());
        if(fertiliser.isEmpty() || toSkip.getFertiliser().contains(fertiliser.get())){
            return Optional.empty();
        }else{
            toSkip.getFertiliser().add(fertiliser.get());
        }

        Optional<Long> water = processMapping(mappings.fertiliserWater, fertiliser.get());
        if(water.isEmpty() || toSkip.getWater().contains(water.get())){
            return Optional.empty();
        }else{
            toSkip.getWater().add(water.get());
        }

        Optional<Long> light = processMapping(mappings.waterLight, water.get());
        if(light.isEmpty() || toSkip.getLight().contains(light.get())){
            return Optional.empty();
        }else{
            toSkip.getLight().add(light.get());
        }

        Optional<Long> temperature = processMapping(mappings.lightTemperature, light.get());
        if(temperature.isEmpty() || toSkip.getTemperature().contains(temperature.get())){
            return Optional.empty();
        }else{
            toSkip.getTemperature().add(temperature.get());
        }

        Optional<Long> humidity = processMapping(mappings.temperatureHumidity, temperature.get());
        if(humidity.isEmpty() || toSkip.getHumidity().contains(humidity.get())){
            return Optional.empty();
        }else{
            toSkip.getHumidity().add(humidity.get());
        }

        return processMapping(mappings.humidityLocation, humidity.get());
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
        for (; input.get(index).matches("\\d.*"); index++) {
            seedSoil.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> soilFertiliser = new ArrayList<>();
        for (; input.get(index).matches("\\d.*"); index++) {
            soilFertiliser.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> fertiliserWater = new ArrayList<>();
        for (; input.get(index).matches("\\d.*"); index++) {
            fertiliserWater.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> waterLight = new ArrayList<>();
        for (; input.get(index).matches("\\d.*"); index++) {
            waterLight.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> lightTemperature = new ArrayList<>();
        for (; input.get(index).matches("\\d.*"); index++) {
            lightTemperature.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> temperatureHumidity = new ArrayList<>();
        for (; input.get(index).matches("\\d.*"); index++) {
            temperatureHumidity.add(extractMap(input.get(index)));
        }
        index += 2;

        List<Mapping> humidityLocation = new ArrayList<>();
        for (; index < input.size(); index++) {
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
        return Mapping.builder()
                .destinationRangeStart(parseLong(values[0]))
                .sourceRangeStart(parseLong(values[1]))
                .rangeLength(parseLong(values[2]))
                .build();
    }

    record Mappings(
            List<Mapping> seedSoil,
            List<Mapping> soilFertiliser,
            List<Mapping> fertiliserWater,
            List<Mapping> waterLight,
            List<Mapping> lightTemperature,
            List<Mapping> temperatureHumidity,
            List<Mapping> humidityLocation
    ) {
    }
}

@Builder
@Getter
class Mapping {
    private final long destinationRangeStart;
    private final long sourceRangeStart;
    private final long rangeLength;
    private boolean explored;

    public Mapping toggleExplored() {
        explored = true;
        return this;
    }
}

@Getter
class ToSkip {
    Set<Long> soil = new HashSet<>();
    Set<Long> fertiliser= new HashSet<>();
    Set<Long> water= new HashSet<>();
    Set<Long> light= new HashSet<>();
    Set<Long> temperature= new HashSet<>();
    Set<Long> humidity= new HashSet<>();
}

