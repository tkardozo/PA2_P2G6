from random import seed, randint

seed('PA2_P2G6')
position = {car: 0 for car in range(100)}


def main(iterations=1000):
    with open('source.txt', 'w') as file:
        for i in range(iterations):
            car = randint(0, 99)
            msg_type = ['00', '01', '02'][randint(0, 2)]
            file.write('{:03d}|{:03d}|{}{}\n'.format(car, i, msg_type, make_msg(car, msg_type)))


def make_msg(car, msg_type):
    if msg_type == '01':
        speed = randint(0, 150)
        position[car] += speed
        return '|{}|{}'.format(speed, position[car])
    if msg_type == '02':
        return '|{}'.format(''.join([chr(randint(65, 90)) for x in range(2)]))
    return ''


main()