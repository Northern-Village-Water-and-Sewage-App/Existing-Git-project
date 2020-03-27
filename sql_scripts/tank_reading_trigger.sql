create function is_caution_level(reading_pk int) returns table(is_caution bool) as
$$
begin
    return QUERY select tr.current_height <= tm.tank_warning_level
                 from water_tank_readings as tr
                          join water_tanks_models tm on tr.tank_model_fk = tm.pk
                 where tr.pk = reading_pk;
end;
$$
language plpgsql;


create function add_to_worklist2() returns trigger as
$$
begin
    if is_caution_level(new.pk) then
        insert into manager_worklist (time_estimate_fk, resident_fk, tank_type_fk)
        SELECT 6, u.pk, 1
        from water_tank_readings tr
                 join residents u on tr.tank_owner_fk = u.pk
        where tr.pk = new.pk;
    end if;
    return new;
end;
$$
    language plpgsql;


create trigger add_to_worklist
    after insert
    on water_tank_readings
    for each row
execute procedure public.add_to_worklist2();


drop trigger add_to_worklist on water_tank_readings;
drop function add_to_worklist2();



insert into water_tank_readings (current_height, tank_owner_fk, tank_model_fk)
values (180, 1, 1);

insert into water_tank_readings (current_height, tank_owner_fk, tank_model_fk)
values (50, 1, 1);
