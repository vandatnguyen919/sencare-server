package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.appointment.Appointment;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.timeslot.Timeslot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppointmentGenerator {

    private static final String[] APPOINTMENT_STATUSES = {
            "Đã đặt lịch", "Đã xác nhận", "Đang chờ", "Hoàn thành",
            "Đã hủy", "Không đến", "Đang điều trị", "Tái khám"
    };

    private static final String[] APPOINTMENT_DESCRIPTIONS = {
            "Khám tổng quát định kỳ và kiểm tra sức khỏe toàn diện.",
            "Tư vấn về tình trạng sức khỏe hiện tại và kế hoạch điều trị.",
            "Theo dõi tiến triển bệnh và điều chỉnh phương pháp điều trị.",
            "Khám chuyên khoa để chẩn đoán và đưa ra phương án điều trị phù hợp.",
            "Tái khám sau điều trị để đánh giá kết quả và hướng dẫn tiếp theo.",
            "Khám cấp cứu do triệu chứng bất thường cần được xử lý ngay.",
            "Tư vấn về chế độ dinh dưỡng và lối sống phù hợp với tình trạng sức khỏe.",
            "Kiểm tra và theo dõi các chỉ số sinh hiệu quan trọng.",
            "Thảo luận về kết quả xét nghiệm và hướng điều trị tiếp theo.",
            "Hướng dẫn sử dụng thuốc và lưu ý trong quá trình điều trị.",
            "Đánh giá tình trạng phục hồi sau phẫu thuật hoặc điều trị.",
            "Tư vấn về các biện pháp phòng ngừa và chăm sóc sức khỏe.",
            "Khám sàng lọc để phát hiện sớm các vấn đề sức khỏe tiềm ẩn.",
            "Theo dõi và điều chỉnh liều lượng thuốc điều trị.",
            "Tư vấn về kế hoạch điều trị dài hạn và quản lý bệnh mãn tính."
    };

    public static List<Appointment> generateSampleAppointments(List<Doctor> doctors, List<MyUser> users, List<Timeslot> timeslots) {
        List<Appointment> appointments = new ArrayList<>();
        Random random = new Random();

        // Group timeslots by potential appointments (1-3 timeslots per appointment)
        List<Timeslot> availableTimeslots = timeslots.stream()
                .filter(t -> !t.isOccupied())
                .collect(ArrayList::new, (list, item) -> list.add(item), ArrayList::addAll);

        int i = 0;
        while (i < availableTimeslots.size() && random.nextDouble() < 0.7) {
            Appointment appointment = new Appointment();
            appointment.setDescription(APPOINTMENT_DESCRIPTIONS[random.nextInt(APPOINTMENT_DESCRIPTIONS.length)]);
            appointment.setStatus(APPOINTMENT_STATUSES[random.nextInt(APPOINTMENT_STATUSES.length)]);

            // Assign doctor and user
            Doctor doctor = !doctors.isEmpty() ? doctors.get(random.nextInt(doctors.size())) : null;
            MyUser user = !users.isEmpty() ? users.get(random.nextInt(users.size())) : null;

            appointment.setDoctor(doctor);
            appointment.setUser(user);

            // Assign 1-3 timeslots to this appointment
            int numTimeslots = 1 + random.nextInt(Math.min(3, availableTimeslots.size() - i));
            List<Timeslot> appointmentTimeslots = new ArrayList<>();

            for (int j = 0; j < numTimeslots && i < availableTimeslots.size(); j++, i++) {
                Timeslot timeslot = availableTimeslots.get(i);
                timeslot.setAppointment(appointment);
                timeslot.setOccupied(true);
                appointmentTimeslots.add(timeslot);
            }

            appointment.setTimeslots(appointmentTimeslots);
            appointments.add(appointment);
        }

        return appointments;
    }

    public static Appointment createAppointment(Doctor doctor, MyUser user, List<Timeslot> timeslots, String status) {
        Random random = new Random();
        Appointment appointment = new Appointment();

        appointment.setDescription(APPOINTMENT_DESCRIPTIONS[random.nextInt(APPOINTMENT_DESCRIPTIONS.length)]);
        appointment.setStatus(status);
        appointment.setDoctor(doctor);
        appointment.setUser(user);

        // Set up bidirectional relationship
        List<Timeslot> appointmentTimeslots = new ArrayList<>();
        for (Timeslot timeslot : timeslots) {
            timeslot.setAppointment(appointment);
            timeslot.setOccupied(true);
            appointmentTimeslots.add(timeslot);
        }
        appointment.setTimeslots(appointmentTimeslots);

        return appointment;
    }

    public static List<Appointment> generateAppointmentsForDoctor(Doctor doctor, List<MyUser> users, List<Timeslot> doctorTimeslots) {
        List<Appointment> appointments = new ArrayList<>();
        Random random = new Random();

        List<Timeslot> availableTimeslots = doctorTimeslots.stream()
                .filter(t -> !t.isOccupied())
                .collect(ArrayList::new, (list, item) -> list.add(item), ArrayList::addAll);

        int i = 0;
        while (i < availableTimeslots.size() && random.nextDouble() < 0.6) {
            MyUser randomUser = users.get(random.nextInt(users.size()));
            String status = APPOINTMENT_STATUSES[random.nextInt(APPOINTMENT_STATUSES.length)];

            // Take 1-2 timeslots for each appointment
            int numTimeslots = 1 + random.nextInt(Math.min(2, availableTimeslots.size() - i));
            List<Timeslot> appointmentTimeslots = new ArrayList<>();

            for (int j = 0; j < numTimeslots && i < availableTimeslots.size(); j++, i++) {
                appointmentTimeslots.add(availableTimeslots.get(i));
            }

            Appointment appointment = createAppointment(doctor, randomUser, appointmentTimeslots, status);
            appointments.add(appointment);
        }

        return appointments;
    }

    public static List<Appointment> generateAppointmentsForUser(MyUser user, List<Doctor> doctors, List<Timeslot> availableTimeslots) {
        List<Appointment> appointments = new ArrayList<>();
        Random random = new Random();

        // Generate 1-3 appointments per user
        int numAppointments = 1 + random.nextInt(3);
        int timeslotIndex = 0;

        for (int i = 0; i < numAppointments && timeslotIndex < availableTimeslots.size(); i++) {
            // Take 1-2 timeslots per appointment
            int numTimeslots = 1 + random.nextInt(Math.min(2, availableTimeslots.size() - timeslotIndex));
            List<Timeslot> appointmentTimeslots = new ArrayList<>();

            for (int j = 0; j < numTimeslots && timeslotIndex < availableTimeslots.size(); j++, timeslotIndex++) {
                Timeslot timeslot = availableTimeslots.get(timeslotIndex);
                if (!timeslot.isOccupied()) {
                    appointmentTimeslots.add(timeslot);
                }
            }

            if (!appointmentTimeslots.isEmpty()) {
                Doctor doctor = appointmentTimeslots.get(0).getDoctor() != null ?
                        appointmentTimeslots.get(0).getDoctor() :
                        doctors.get(random.nextInt(doctors.size()));

                String status = APPOINTMENT_STATUSES[random.nextInt(APPOINTMENT_STATUSES.length)];
                Appointment appointment = createAppointment(doctor, user, appointmentTimeslots, status);
                appointments.add(appointment);
            }
        }

        return appointments;
    }
}
