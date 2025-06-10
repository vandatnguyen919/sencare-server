package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.timeslot.Timeslot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeslotGenerator {

    private static final LocalTime[] START_TIMES = {
            LocalTime.of(8, 0),   // 8:00 AM
            LocalTime.of(8, 30),  // 8:30 AM
            LocalTime.of(9, 0),   // 9:00 AM
            LocalTime.of(9, 30),  // 9:30 AM
            LocalTime.of(10, 0),  // 10:00 AM
            LocalTime.of(10, 30), // 10:30 AM
            LocalTime.of(11, 0),  // 11:00 AM
            LocalTime.of(11, 30), // 11:30 AM
            LocalTime.of(13, 0),  // 1:00 PM
            LocalTime.of(13, 30), // 1:30 PM
            LocalTime.of(14, 0),  // 2:00 PM
            LocalTime.of(14, 30), // 2:30 PM
            LocalTime.of(15, 0),  // 3:00 PM
            LocalTime.of(15, 30), // 3:30 PM
            LocalTime.of(16, 0),  // 4:00 PM
            LocalTime.of(16, 30), // 4:30 PM
            LocalTime.of(17, 0),  // 5:00 PM
            LocalTime.of(17, 30)  // 5:30 PM
    };

    private static final int SLOT_DURATION_MINUTES = 30; // 30-minute slots

    public static List<Timeslot> generateSampleTimeslots(List<Doctor> doctors, List<HosServ> services) {
        List<Timeslot> timeslots = new ArrayList<>();
        Random random = new Random();
        LocalDate currentDate = LocalDate.now();

        for (Doctor doctor : doctors) {
            // Generate timeslots for next 30 days
            for (int dayOffset = 0; dayOffset < 30; dayOffset++) {
                LocalDate date = currentDate.plusDays(dayOffset);

                // Skip weekends for most doctors (80% chance to skip)
                if ((date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)
                        && random.nextDouble() < 0.8) {
                    continue;
                }

                // Generate 4-8 timeslots per working day
                int slotsPerDay = 4 + random.nextInt(5);
                List<LocalTime> dayStartTimes = new ArrayList<>();

                // Select random start times for the day
                for (int i = 0; i < slotsPerDay; i++) {
                    LocalTime startTime = START_TIMES[random.nextInt(START_TIMES.length)];
                    if (!dayStartTimes.contains(startTime)) {
                        dayStartTimes.add(startTime);
                    }
                }

                // Create timeslots for selected times
                for (LocalTime startTime : dayStartTimes) {
                    Timeslot timeslot = new Timeslot();
                    timeslot.setStartTime(startTime);
                    timeslot.setEndTime(startTime.plusMinutes(SLOT_DURATION_MINUTES));
                    timeslot.setDate(date);
                    timeslot.setOccupied(false); // Initially not occupied
                    timeslot.setDoctor(doctor);

                    // Randomly assign a service (optional)
                    if (!services.isEmpty() && random.nextDouble() < 0.3) { // 30% chance
                        HosServ service = services.get(random.nextInt(services.size()));
                        timeslot.setHosServ(service);
                    }

                    timeslots.add(timeslot);
                }
            }
        }

        return timeslots;
    }

    public static List<Timeslot> generateTimeslotsForDoctor(Doctor doctor, LocalDate startDate, int numberOfDays) {
        List<Timeslot> timeslots = new ArrayList<>();
        Random random = new Random();

        for (int dayOffset = 0; dayOffset < numberOfDays; dayOffset++) {
            LocalDate date = startDate.plusDays(dayOffset);

            // Skip weekends
            if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
                continue;
            }

            // Morning slots (8:00 AM - 12:00 PM)
            List<Timeslot> morningSlots = generateSlotsForPeriod(
                    doctor, date, LocalTime.of(8, 0), LocalTime.of(12, 0), random
            );
            timeslots.addAll(morningSlots);

            // Afternoon slots (1:00 PM - 6:00 PM)
            List<Timeslot> afternoonSlots = generateSlotsForPeriod(
                    doctor, date, LocalTime.of(13, 0), LocalTime.of(18, 0), random
            );
            timeslots.addAll(afternoonSlots);
        }

        return timeslots;
    }

    private static List<Timeslot> generateSlotsForPeriod(Doctor doctor, LocalDate date,
                                                         LocalTime startTime, LocalTime endTime, Random random) {
        List<Timeslot> slots = new ArrayList<>();
        LocalTime currentTime = startTime;

        while (currentTime.isBefore(endTime)) {
            // 70% chance to create a slot (some gaps for breaks)
            if (random.nextDouble() < 0.7) {
                Timeslot timeslot = new Timeslot();
                timeslot.setStartTime(currentTime);
                timeslot.setEndTime(currentTime.plusMinutes(SLOT_DURATION_MINUTES));
                timeslot.setDate(date);
                timeslot.setOccupied(false);
                timeslot.setDoctor(doctor);
                slots.add(timeslot);
            }
            currentTime = currentTime.plusMinutes(SLOT_DURATION_MINUTES);
        }

        return slots;
    }

    public static Timeslot createTimeslot(Doctor doctor, LocalDate date, LocalTime startTime, HosServ service) {
        Timeslot timeslot = new Timeslot();
        timeslot.setStartTime(startTime);
        timeslot.setEndTime(startTime.plusMinutes(SLOT_DURATION_MINUTES));
        timeslot.setDate(date);
        timeslot.setOccupied(false);
        timeslot.setDoctor(doctor);
        timeslot.setHosServ(service);
        return timeslot;
    }

    public static List<Timeslot> generateWeeklyTimeslots(Doctor doctor, List<HosServ> services) {
        List<Timeslot> timeslots = new ArrayList<>();
        Random random = new Random();
        LocalDate startOfWeek = LocalDate.now().with(java.time.DayOfWeek.MONDAY);

        // Generate for current week (Monday to Friday)
        for (int dayOffset = 0; dayOffset < 5; dayOffset++) {
            LocalDate date = startOfWeek.plusDays(dayOffset);

            // Standard working hours: 8 AM to 5 PM with lunch break
            LocalTime[] workingHours = {
                    LocalTime.of(8, 0), LocalTime.of(8, 30), LocalTime.of(9, 0), LocalTime.of(9, 30),
                    LocalTime.of(10, 0), LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30),
                    LocalTime.of(13, 0), LocalTime.of(13, 30), LocalTime.of(14, 0), LocalTime.of(14, 30),
                    LocalTime.of(15, 0), LocalTime.of(15, 30), LocalTime.of(16, 0), LocalTime.of(16, 30)
            };

            for (LocalTime startTime : workingHours) {
                HosServ service = services.isEmpty() ? null :
                        (random.nextDouble() < 0.4 ? services.get(random.nextInt(services.size())) : null);

                Timeslot timeslot = createTimeslot(doctor, date, startTime, service);
                timeslots.add(timeslot);
            }
        }

        return timeslots;
    }
}
